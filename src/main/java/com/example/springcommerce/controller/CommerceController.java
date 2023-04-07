package com.example.springcommerce.controller;

import com.example.springcommerce.model.*;
import com.example.springcommerce.service.ICategoryService;
import com.example.springcommerce.service.ICustomerService;
import com.example.springcommerce.service.IProductService;
import com.example.springcommerce.service.IRoleService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("cart")
public class CommerceController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @GetMapping(value = "/")
//    public String _login() {
//        return "redirect:/login";
//    }
    @GetMapping("/home")
    public ModelAndView home(HttpServletRequest req, HttpSession session) {
        ModelAndView mv = new ModelAndView("redirect:/login");

        if (session != null && session.getAttribute("customerId") != null) {
            UUID customerId = UUID.fromString(String.valueOf(session.getAttribute("customerId")));
            Customer customer = customerService.selectById(customerId);
            if (customer == null) {
                mv.addObject("status", "warning");
                mv.addObject("message", "The account has been deleted from the database");
                return mv;
            }
            String username = (String) session.getAttribute("username");
            List<Category> categories = categoryService.getAllCategories();
            List<Product> products = productService.selectAll();
            mv.addObject("username", username);
            mv.addObject("products", products);
            mv.addObject("categories", categories);
            mv.setViewName("home");
        }
        return mv;
    }

    @GetMapping(value = "/login")
    public ModelAndView loginG(HttpServletRequest req, HttpSession session) {
        Cookie[] cookies = req.getCookies();
        ModelAndView mv = new ModelAndView("login");

        if (session != null && session.getAttribute("customerId") != null) {
            UUID customerId = UUID.fromString(String.valueOf(session.getAttribute("customerId")));
            Customer customer = customerService.selectById(customerId);
            if (customer != null) {
                mv.setViewName("redirect:/home");
            } else {
                mv.addObject("status", "warning");
                mv.addObject("message", "The account has been deleted from the database");
            }
        } else {
            if (cookies != null) {
                for (Cookie cookie: cookies) {
                    if (cookie.getName().equals("customerId")) {
                        UUID customerId = UUID.fromString(cookie.getValue());
                        Customer customer = customerService.selectById(customerId);
                        if (customer != null) {
                            session.setAttribute("customerId", customerId.toString());
                            session.setAttribute("username", customer.getUsername());
                            mv.setViewName("redirect:/home");
                            return mv;
                        }
                        break;
                    }
                }
            }
        }
        return mv;
    }

    @GetMapping(value = "/register")
    public ModelAndView registerG(HttpSession session) {
        ModelAndView mv = new ModelAndView("register");
        if (session != null && session.getAttribute("customerId") != null) {
            UUID customerId = UUID.fromString((String) session.getAttribute("customerId"));
            Customer customer = customerService.selectById(customerId);
            if (customer == null) {
                mv.addObject("status", "warning");
                mv.addObject("message", "The account has been deleted from the database");
            } else {
                mv.setViewName("redirect:/home");
            }
        }
        return mv;
    }

    @PostMapping(value = "/login")
    public ModelAndView loginP(@RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam(name = "myRemember", required=false) boolean remember, HttpSession session,
                               HttpServletResponse resp) {
//        ModelAndView mv = new ModelAndView("login");
//        Customer customer = customerService.selectByUsername(username);
//
//        if (customer == null) {
//            mv.addObject("status", "error");
//            mv.addObject("message", "Account not registered");
//        } else if (!customer.getPassword().equals(password)){
//            mv.addObject("status", "error");
//            mv.addObject("message", "Wrong password");
//        } else {
//            if (remember) {
//                Cookie cookie = new Cookie("customerId", customer.getId().toString());
//                cookie.setMaxAge(60 * 60 * 24 * 30);
//                resp.addCookie(cookie);
//            }
//            session.setAttribute("customerId", customer.getId().toString());
//            session.setAttribute("username", customer.getUsername());
//            mv.setViewName("redirect:/home");
//        }
//        return mv;

        try {
            // Gọi hàm authenticate để xác thực thông tin đăng nhập
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            ModelAndView mv = new ModelAndView("/home");
            Customer customer = customerService.selectByUsername(username);
            if (!authentication.isAuthenticated()) {
                mv.addObject("status", "error");
                mv.addObject("message", "Account not registered");
            } else {
                if (remember) {
                    Cookie cookie = new Cookie("customerId", customer.getId().toString());
                    cookie.setMaxAge(60 * 60 * 24 * 30);
                    resp.addCookie(cookie);
                }
                session.setAttribute("customerId", customer.getId().toString());
                session.setAttribute("username", customer.getUsername());
//                mv.setViewName("/home");
//                mv.setViewName("redirect:/home");
            }
            return mv;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("/login");
        }
    }

    @PostMapping(value = "/register")
    public ModelAndView registerP(@RequestParam("name") String name, @RequestParam("username") String username,
                                  @RequestParam("email") String email, @RequestParam("password") String password,
                                  @RequestParam("gender") String gender, HttpSession session,
                                  @RequestParam(name = "dob") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob,
                                  @RequestParam("role") String role) {
        ModelAndView mv = new ModelAndView("register");

        if (customerService.selectByUsername(username) != null) {
            mv.addObject("status", "warning");
            mv.addObject("message", "Username already exists");
        } else {
            Role newrole = roleService.findById(Integer.parseInt(role));
            Customer customer =customerService.insert(new Customer(UUID.randomUUID(), name, username, email, password, dob, gender, newrole));
            if (customer != null) {
                mv.addObject("status", "success");
                mv.addObject("message", "Account has been successfully registered");
                mv.setViewName("redirect:/login");
            }
        }
        return mv;
    }

    @GetMapping(value = "/order")
    public ModelAndView orderG() {
//        List<Student> students = studentDAO.getStudentAll();
//        ModelAndView modelAndView = new ModelAndView("home");
//        if (!students.isEmpty())
//            modelAndView.addObject("students", students);
//        return modelAndView;
        return null;
    }

    @GetMapping("/detail-product/{id}")
    public ModelAndView viewAProduct(@PathVariable("id") int id){
        Product product = productService.selectById(id);
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("product", product);
        return modelAndView;
    }

//    @GetMapping("/get-products/{id}")
//    public ModelAndView displayCategory(@PathVariable("id") Integer id){
//        Optional<Category> categories = categoryService.findById(id);
//        Iterable<Product> products = productService.findAllByCategory(categories.get());
//
//        ModelAndView modelAndView = new ModelAndView("home");
//        modelAndView.addObject("categories", categories.get());
//        modelAndView.addObject("products", products);
//        return modelAndView;
//    }

    //Cart
    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }

    @GetMapping("/addtocard/{id}")
    public String addToCart(@PathVariable Integer id, @ModelAttribute Cart cart, @RequestParam("action") String action) {
        Product product = productService.selectById(id);
        if (action.equals("show")) {
            cart.addProduct(product);
            return "redirect:/cart";
        }
        cart.addProduct(product);
        return "redirect:/home";
    }

    @GetMapping("/deletetocard/{id}")
    public String deleteToCart(@PathVariable Integer id, @ModelAttribute Cart cart) {
        Product product = productService.selectById(id);
        cart.deleteProduct(product);
        return "/cart";
    }
}

package com.example.springcommerce.controller;

import com.example.springcommerce.model.Customer;
import com.example.springcommerce.model.Product;
import com.example.springcommerce.service.ICustomerService;
import com.example.springcommerce.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.UUID;

@Controller
@RequestMapping(value = "/product")
public class CommerceController2 {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping(value = "/cart")
    public ModelAndView cartG(HttpSession session) {
        ModelAndView mv = new ModelAndView("cart");
        UUID customerId = UUID.fromString(String.valueOf(session.getAttribute("customerId")));
        Customer customer = customerService.selectById(customerId);

        Set<Product> products = customer.getProducts();
        if (products.size() == 0) {
            mv.addObject("status", "warning");
            mv.addObject("message", "There are no products in the cart");
        } else {
            mv.addObject("products", products);
        }
        return mv;
    }

    @GetMapping(value = "/product/{id}")
    public ModelAndView informationProduct(@PathVariable String productId, HttpSession session) {
        ModelAndView mv = new ModelAndView("detail");
//        UUID customerId = UUID.fromString(String.valueOf(session.getAttribute("customerId")));
        Product product = productService.selectById(Integer.parseInt(productId));

        if (product == null) {
            mv.addObject("status", "warning");
            mv.addObject("message", "The product is not in the database");
        } else {
            mv.addObject("product", product);
        }
        return mv;
    }

    @PostMapping(value = "/addToCart/{id}")
    public ModelAndView addToCart(@PathVariable String productId, HttpSession session) {
        ModelAndView mv = new ModelAndView("redirect:/home");
        UUID customerId = UUID.fromString(String.valueOf(session.getAttribute("customerId")));
        Customer customer = customerService.selectById(customerId);
        int idProduct = Integer.parseInt(productId);

        Product product = customer.getProducts().stream().filter(p -> p.getId() == idProduct).findAny().orElse(null);
        if (product != null) {
            product.setQuantity(product.getQuantity() + 1);
        } else {
            customer.getProducts().add(new Product(product.getId(), product.getName(), product.getQuantity(), product.getPrice(),
                    product.getColor(), product.getDescription(), product.getImage(), product.getBrandId()));
        }
        mv.addObject("status", "success");
        mv.addObject("message", "The product has been added to cart");
        return mv;
    }

//    @PostMapping(value = "/removeFromCart")
//    public ResponseEntity<Order> removeFromCart(@RequestBody Order cart) {
//        if (cartService.deleteById(cart.getId()))
//            return ResponseEntity.ofNullable(cart);
//        return ResponseEntity.ofNullable(null);
//    }

    @GetMapping(value = "/order")
    public ModelAndView orderG() {
//        List<Student> students = studentDAO.getStudentAll();
//        ModelAndView modelAndView = new ModelAndView("home");
//        if (!students.isEmpty())
//            modelAndView.addObject("students", students);
//        return modelAndView;
        return null;
    }
}

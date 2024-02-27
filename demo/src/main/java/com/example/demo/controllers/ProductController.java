package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; //может не тот импорт
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name ="title", required = false) String title,Model model){
        model.addAttribute("products", productService.listProducts(title));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable int id, Model model)
    {
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }

    @GetMapping("/products/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/products/add")
    public String addProductSubmit(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("/products/{id}/edit")
    public String editProductForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PostMapping("/products/{id}/edit")
    public String editProductSubmit(@PathVariable int id, @ModelAttribute Product updatedProduct) {
        productService.updateProduct(id, updatedProduct);
        return "redirect:/";
    }

    @PostMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}



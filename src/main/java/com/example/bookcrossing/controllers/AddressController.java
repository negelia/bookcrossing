package com.example.bookcrossing.controllers;

import com.example.bookcrossing.models.Address;
import com.example.bookcrossing.models.Author;
import com.example.bookcrossing.models.Review;
import com.example.bookcrossing.repo.AddressRepository;
import com.example.bookcrossing.repo.BookRepository;
import com.example.bookcrossing.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/address")
    public String addressMain( Model model){
        Iterable<Address> address = addressRepository.findAll();
        model.addAttribute("address", address);
        return "/address/address-main";
    }

    @GetMapping("/address/add")
    public String Main(Address address, Model model){
        Iterable<Address> addresses = addressRepository.findAll();
        model.addAttribute("addresses",addresses);
        return "/address/address-add";
    }

    @PostMapping("/address/add")
    public String addressAdd( @RequestParam String city,
                              @RequestParam String street,
                              @RequestParam String building,
                              Model model)
    {
        Address address = new Address(city, street, building);
        addressRepository.save(address);
        return "redirect:/address";
    }

    @GetMapping("/address/{id}/edit")
    public String addressEdit(@PathVariable("id")long id,
                             Model model)
    {
        if(!addressRepository.existsById(id)){
            return "redirect:/address";
        }
        Optional<Address> post = addressRepository.findById(id);
        ArrayList<Address> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("address",res);
        return "/address/address-edit";
    }
    @PostMapping("/address/{id}/edit")
    public String addressUpdate(@PathVariable("id")long id,
                               @RequestParam String city,
                               @RequestParam String street,
                               @RequestParam String building,
                               Model model)
    {
        Address address = addressRepository.findById(id).orElseThrow();
        address.setCity(city);
        address.setStreet(street);
        address.setBuilding(building);
        addressRepository.save(address);
        return "redirect:/address";
    }

    @PostMapping("/address/{id}/remove")
    public String addressDelete(@PathVariable("id") long id, Model model){
        Address address = addressRepository.findById(id).orElseThrow();
        addressRepository.delete(address);
        return "redirect:/address";
    }
}

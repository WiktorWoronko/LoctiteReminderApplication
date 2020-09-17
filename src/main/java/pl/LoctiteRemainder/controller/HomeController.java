package pl.LoctiteRemainder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.LoctiteRemainder.model.Adhesive;
import pl.LoctiteRemainder.service.AdhesiveService;

import java.util.Optional;
/*
@RestController
@RequestMapping("/remainder")
public class HomeController {

    private AdhesiveService adhesiveService;

    @Autowired
    public HomeController(AdhesiveService adhesiveService){
        this.adhesiveService=adhesiveService;
    }

    @GetMapping("/getAllAdhesives")
    public Iterable<Adhesive> getAll(){
        return adhesiveService.getAll();
    }
    @GetMapping("/getAdhesive/{index}")
    public Optional<Adhesive> getById(@PathVariable int index){
        return adhesiveService.findById(index);
    }
    @PostMapping("/addAdhesive")
    public Adhesive saveAdhesive(@RequestBody Adhesive adhesive){
        return adhesiveService.save(adhesive);
    }
    @PutMapping("/updateAdhesive")
    public Adhesive updateAdhesive(@RequestBody Adhesive adhesive){
        return adhesiveService.update(adhesive);
    }
    @DeleteMapping("/deleteAdhesive/{index}")
    public void deleteAdhesive(@PathVariable int index){
        adhesiveService.delete(index);
    }

}


 */
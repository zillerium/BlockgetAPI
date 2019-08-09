package hello.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import hello.model.Pledge;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class GreetingController {

    private List<Pledge> pledges = new ArrayList<>();
   // private static final String template = "Hello, %s!";
    private final AtomicLong nextId = new AtomicLong();

    @GetMapping ("/greeting")
    public String getHelloMessage() {
        return "hello from spring";
    }

    @PostMapping("/pledges")
    public Pledge createNewPledge(@RequestBody Pledge pledge) {
        // set pledge to have id
        pledge.setId(nextId.incrementAndGet());
        pledges.add(pledge);
        return pledge;
    }

    @GetMapping("/pledges")
    public List<Pledge> getListPledges() {
        return pledges;
    }

    @GetMapping("/pledges/{id}")
    public Pledge getOnePledge(@PathVariable("id") long pledgeId) {
        for (Pledge pledge : pledges)  {
            if (pledge.getId() == pledgeId) {
                return pledge;
            }
        }

        return null;
    }

    //public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    //    return new Greeting(counter.incrementAndGet(),
    //            String.format(template, name));
   // }



}
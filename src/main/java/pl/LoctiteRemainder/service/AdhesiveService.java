package pl.LoctiteRemainder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.LoctiteRemainder.model.Adhesive;
import pl.LoctiteRemainder.repository.AdhesiveRepository;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AdhesiveService {

    private static final Logger LOGGER = Logger.getLogger(AdhesiveService.class.getName());


    private AdhesiveRepository adhesiveRepository;
    private final EmailService emailService;

    @Autowired
    public AdhesiveService(AdhesiveRepository adhesiveRepository, EmailService emailService) {
        this.adhesiveRepository = adhesiveRepository;
        this.emailService = emailService;
    }

    public void sendRemindEmail() {
        JLabel myLabel = new JLabel();
        myLabel.setFont(myLabel.getFont().deriveFont(Font.PLAIN));
        List<Adhesive> listOfExpiredProducts = compare();
        if (listOfExpiredProducts.isEmpty()) {
            System.out.println("Brak produktów przeterminowanych");
        } else {
            emailService.sendSimpleMessage("odebranetest95@gmail.com",
                    "Aktualna lista towarów zagrożona przeterminowaniem",
                    "Produkty zbliżające się do swojego terminu ważności: \n" + listOfExpiredProducts.stream().map(n -> "Nazwa produktu: " + n.getProductName() + "  |  Numer IDH: " + n.getIdhNumber() + "  |  Ilość produktu: " + n.getAmountOfProduct() + "  |  Data ważności: " + n.getExpiryDate()).collect(Collectors.joining("\n")));
        }
    }


    private List<Adhesive> compare() {
        List<Adhesive> listOfAllProducts;
        listOfAllProducts = adhesiveRepository.findAll();

        List<Adhesive> listOfProductToRemind = listOfAllProducts.stream().filter(adh -> adh.getRemindDate().equals(LocalDate.now()) || adh.getRemindDate().isBefore(LocalDate.now())).collect(Collectors.toList());


        return listOfProductToRemind;
    }


    public Adhesive save(Adhesive adhesive) {
        return adhesiveRepository.save(adhesive);
    }

    public void deleteAdhesive(Adhesive adhesive) {
        if (adhesive != null) {
            adhesiveRepository.delete(adhesive);
        }
    }

    public Iterable<Adhesive> getAll(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return adhesiveRepository.findAll();
        } else {
            return adhesiveRepository.search(filterText);
        }
    }


}

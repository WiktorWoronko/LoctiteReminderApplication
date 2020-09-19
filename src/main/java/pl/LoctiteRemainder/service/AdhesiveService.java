package pl.LoctiteRemainder.service;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.LoctiteRemainder.model.Adhesive;
import pl.LoctiteRemainder.repository.AdhesiveRepository;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AdhesiveService {

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
        List<Adhesive> listOfProductsToRemind = compareRemindDatesWithCurrentTimeOrPastTime();
        if (listOfProductsToRemind.isEmpty()) {
            Notification.show("There are no products with given reminder dates ");
        } else {
            emailService.sendSimpleMessage(emailService.chooseYourEmail(),
                    "The current list of goods at risk of expiry",
                    "Products approaching their expiry date: \n" + listOfProductsToRemind.stream().map(n -> "Product name: " + n.getProductName() + "  |  Product IDH: " + n.getIdhNumber() + "  |  Product amount: " + n.getAmountOfProduct() + "  |  Expiry date: " + n.getExpiryDate()).collect(Collectors.joining("\n")));
        }
    }


    private List<Adhesive> compareRemindDatesWithCurrentTimeOrPastTime() {
        List<Adhesive> listOfDataBaseProducts;
        listOfDataBaseProducts = adhesiveRepository.findAll();

        List<Adhesive> listOfProductsToRemind = listOfDataBaseProducts.stream().filter(adh -> adh.getRemindDate().equals(LocalDate.now()) || adh.getRemindDate().isBefore(LocalDate.now())).collect(Collectors.toList());


        return listOfProductsToRemind;
    }


    public Adhesive saveAdhesive(Adhesive adhesive) {
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

package pl.LoctiteRemainder.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import pl.LoctiteRemainder.model.Adhesive;
import pl.LoctiteRemainder.model.Email;
import pl.LoctiteRemainder.service.AdhesiveService;
import pl.LoctiteRemainder.service.EmailService;

import java.util.Collection;


@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {
    private EmailForm emailForm;

    private AdhesiveForm adhesiveForm;

    private EmailService emailService;

    private AdhesiveService adhesiveService;

    Grid<Adhesive> grid = new Grid<>(Adhesive.class);

    TextField filterText = new TextField();

    public MainView(AdhesiveService adhesiveService, EmailService emailService) {
        this.adhesiveService = adhesiveService;
        this.emailService = emailService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        adhesiveForm = new AdhesiveForm();

        emailForm = new EmailForm();

        adhesiveForm.addListener(AdhesiveForm.SaveEvent.class, evt -> saveAdhesive(evt));
        adhesiveForm.addListener(AdhesiveForm.DeleteEvent.class, evt -> deleteAdhesive(evt));
        adhesiveForm.addListener(AdhesiveForm.CloseEvent.class, evt -> closeAdhesiveEditor());

        Div content = new Div(grid, adhesiveForm);

        content.addClassName("content");
        content.setSizeFull();
        add(getToolBar(), content);

        updateList();
        closeAdhesiveEditor();
    }

    private void deleteAdhesive(AdhesiveForm.DeleteEvent event) {
        adhesiveService.deleteAdhesive(event.getAdhesive());
        updateList();
        Notification.show("Product has been deleted");
        closeAdhesiveEditor();
    }

    private void saveAdhesive(AdhesiveForm.SaveEvent event) {
        adhesiveService.saveAdhesive(event.getAdhesive());
        updateList();
        Notification.show("Product has been added");
        closeAdhesiveEditor();
    }

    private void addAdhesive() {
        grid.asSingleSelect().clear();
        editAdhesive(new Adhesive());
    }

    private void editAdhesive(Adhesive adhesive) {
        if (adhesive == null) {
            closeAdhesiveEditor();
        } else {
            adhesiveForm.setAdhesive(adhesive);
            adhesiveForm.setVisible(true);
            addClassName("editing");
        }

    }

    private void closeAdhesiveEditor() {
        adhesiveForm.setAdhesive(null);
        adhesiveForm.setVisible(false);
        removeClassName("editing");
    }


    private void saveEmail(EmailForm.SaveEvent evt) {
        emailService.saveEmail(evt.getEmail());
        adhesiveService.sendRemindEmail();
        closeEmailEditor();
        Notification.show("Email has been sent");
    }

    private void sendEmail() {
        editEmail(new Email());
        emailForm.addListener(EmailForm.SaveEvent.class, evt -> saveEmail(evt));
        emailForm.addListener(EmailForm.CloseEvent.class, e -> closeEmailEditor());
        add(emailForm);
    }

    private void editEmail(Email email) {
        if (email == null) {
            closeEmailEditor();
        } else {
            emailForm.setEmail(email);
            emailForm.setVisible(true);
            addClassName("mailing");
        }
    }

    private void closeEmailEditor() {
        emailForm.setEmail(null);
        emailForm.setVisible(false);
        removeClassName("mailing");
    }


    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by product name");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addAdhesiveButton = new Button("Add product", click -> addAdhesive());

        Button generateEmailButton = new Button("Generate remind email", click -> sendEmail());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addAdhesiveButton, generateEmailButton);
        toolBar.addClassName("toolbar");

        return toolBar;
    }

    private void updateList() {
        grid.setItems((Collection<Adhesive>) adhesiveService.getAll(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("adhesive-grid");
        grid.setSizeFull();
        grid.setColumns("productName", "idhNumber", "amountOfProduct", "expiryDate", "remindDate");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(evt -> editAdhesive(evt.getValue()));
    }
}

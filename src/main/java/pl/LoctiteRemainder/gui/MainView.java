package pl.LoctiteRemainder.gui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import pl.LoctiteRemainder.model.Adhesive;
import pl.LoctiteRemainder.service.AdhesiveService;

import java.util.Collection;


@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {


    Grid<Adhesive> grid = new Grid<>(Adhesive.class);
    TextField filterText = new TextField();

    AdhesiveForm adhesiveForm;

    private AdhesiveService adhesiveService;


    public MainView(AdhesiveService adhesiveService) {
        this.adhesiveService = adhesiveService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        adhesiveForm = new AdhesiveForm();

        adhesiveForm.addListener(AdhesiveForm.SaveEvent.class, evt -> saveContact(evt));
        adhesiveForm.addListener(AdhesiveForm.DeleteEvent.class, evt -> deleteContact(evt));
        adhesiveForm.addListener(AdhesiveForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, adhesiveForm);

        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);

        updateList();
        closeEditor();

    }

    private void deleteContact(AdhesiveForm.DeleteEvent event) {
        adhesiveService.deleteAdhesive(event.getAdhesive());
        updateList();
        closeEditor();
    }

    private void saveContact(AdhesiveForm.SaveEvent evt) {
        adhesiveService.save(evt.getAdhesive());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        adhesiveForm.setAdhesive(null);
        adhesiveForm.setVisible(false);
        removeClassName("editing");
    }


    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filtruj po nazwie");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addAdhesiveButton = new Button("Dodaj produkt", click -> addAdhesive());

        Button generateEmailButton = new Button("Generuj emaila-przypominajkę", click -> sendEmail());

        HorizontalLayout toolBar = new HorizontalLayout(filterText, addAdhesiveButton, generateEmailButton);
        toolBar.addClassName("toolbar");

        return toolBar;
    }

    private void sendEmail() {

        grid.asSingleSelect().clear();
        adhesiveService.sendRemindEmail();
    }

    private void addAdhesive() {
        grid.asSingleSelect().clear();
        editContact(new Adhesive());
    }

    private void updateList() {
        grid.setItems((Collection<Adhesive>) adhesiveService.getAll(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("adhesive-grid");
        grid.setSizeFull();
        grid.setColumns("productName", "idhNumber", "amountOfProduct", "expiryDate", "remindDate");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(evt -> editContact(evt.getValue()));
    }

    private void editContact(Adhesive adhesive) {
        if (adhesive == null) {
            closeEditor();
        } else {
            adhesiveForm.setAdhesive(adhesive);
            adhesiveForm.setVisible(true);
            addClassName("editing");
        }

    }
}

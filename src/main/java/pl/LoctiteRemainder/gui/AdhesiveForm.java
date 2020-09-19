package pl.LoctiteRemainder.gui;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import pl.LoctiteRemainder.model.Adhesive;

public class AdhesiveForm extends FormLayout {


    TextField productName = new TextField("Product name");
    TextField idhNumber = new TextField("Product IDH");
    TextField amountOfProduct = new TextField("Product amount");
    DatePicker expiryDate = new DatePicker("Expiry date");
    DatePicker remindDate = new DatePicker("Remind date");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Close");

    Binder<Adhesive> binder = new BeanValidationBinder<>(Adhesive.class);

    public AdhesiveForm() {
        addClassName("adhesive-form");

        binder.bindInstanceFields(this);

        add(

                productName,
                idhNumber,
                amountOfProduct,
                expiryDate,
                remindDate,
                createButtonsLayout()
        );

    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> validateAndDelete());
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));
        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()) {
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    private void validateAndDelete() {
        if (binder.isValid()) {
            fireEvent(new DeleteEvent(this, binder.getBean()));
        }
    }


    public void setAdhesive(Adhesive adhesive) {
        binder.setBean(adhesive);
    }

    public static abstract class AdhesiveFormEvent extends ComponentEvent<AdhesiveForm> {
        private Adhesive adhesive;

        protected AdhesiveFormEvent(AdhesiveForm source, Adhesive adhesive) {
            super(source, false);
            this.adhesive = adhesive;
        }

        public Adhesive getAdhesive() {
            return adhesive;
        }
    }

    public static class SaveEvent extends AdhesiveFormEvent {
        SaveEvent(AdhesiveForm source, Adhesive adhesive) {

            super(source, adhesive);
        }
    }

    public static class DeleteEvent extends AdhesiveFormEvent {

        DeleteEvent(AdhesiveForm source, Adhesive adhesive) {

            super(source, adhesive);
        }

    }

    public static class CloseEvent extends AdhesiveFormEvent {
        CloseEvent(AdhesiveForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }


}

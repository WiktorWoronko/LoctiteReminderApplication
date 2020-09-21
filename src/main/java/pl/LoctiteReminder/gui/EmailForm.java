package pl.LoctiteReminder.gui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import pl.LoctiteReminder.model.Email;

public class EmailForm extends FormLayout {


    TextField email = new TextField("Write your email");

    Button send = new Button("Send");
    Button close = new Button("Close");

    Binder<Email> emailBinder = new BeanValidationBinder<>(Email.class);

    public EmailForm() {
        addClassName("email-form");

        emailBinder.bindInstanceFields(this);

        add(
                email,
                createButtonsLayout()
        );

    }


    private Component createButtonsLayout() {
        send.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        send.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        send.addClickListener(click -> validateAndSave());
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        emailBinder.addStatusChangeListener(evt -> send.setEnabled(emailBinder.isValid()));
        return new HorizontalLayout(send, close);
    }

    private void validateAndSave() {
        if (emailBinder.isValid()) {
            fireEvent(new EmailForm.SaveEvent(this, emailBinder.getBean()));
        }
    }

    public void setEmail(Email email) {
        emailBinder.setBean(email);
    }

    public static abstract class EmailFormEvent extends ComponentEvent<EmailForm> {
        private Email email;

        protected EmailFormEvent(EmailForm source, Email email) {
            super(source, false);
            this.email = email;
        }

        public Email getEmail() {
            return email;
        }
    }

    public static class SaveEvent extends EmailFormEvent {
        SaveEvent(EmailForm source, Email email) {

            super(source, email);
        }
    }

    public static class CloseEvent extends EmailForm.EmailFormEvent {
        CloseEvent(EmailForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}


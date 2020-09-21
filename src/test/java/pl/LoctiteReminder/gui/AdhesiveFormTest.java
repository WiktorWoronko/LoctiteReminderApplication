package pl.LoctiteReminder.gui;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.LoctiteReminder.model.Adhesive;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AdhesiveFormTest {

    private List<Adhesive> adhesiveList;
    private Adhesive adhesive;

    @Before
    public void setUpData(){
        adhesive= new Adhesive();
        adhesiveList= new ArrayList<>();
        adhesive.setProductName("Loctite 275");
        adhesive.setIdhNumber("123456");
        adhesive.setAmountOfProduct("12");
        adhesive.setExpiryDate(LocalDate.of(2021,8,21));
        adhesive.setRemindDate(LocalDate.of(2021,6,25));

    }

    @Test
    public void formFieldsPopulatedTest(){
        AdhesiveForm form= new AdhesiveForm();
        form.setAdhesive(adhesive);

        Assert.assertEquals("Loctite 275", form.productName.getValue());
        Assert.assertEquals("123456",form.idhNumber.getValue());
        Assert.assertEquals("12",form.amountOfProduct.getValue());
        Assert.assertEquals(LocalDate.of(2021,8,21),form.expiryDate.getValue());
        Assert.assertEquals(LocalDate.of(2021,6,25),form.remindDate.getValue());
        

    }

    @Test
    public void saveEventHasCorrectValuesTest(){
        AdhesiveForm form= new AdhesiveForm();
        Adhesive adhesive= new Adhesive();
        form.setAdhesive(adhesive);

        form.productName.setValue("Loctite 290");
        form.amountOfProduct.setValue("142");
        form.idhNumber.setValue("987789");
        form.expiryDate.setValue(LocalDate.of(2021,2,21));
        form.remindDate.setValue(LocalDate.of(2021,1,21));

        AtomicReference<Adhesive> savedAdhesiveRef= new AtomicReference<>(null);
        form.addListener(AdhesiveForm.SaveEvent.class, evt-> savedAdhesiveRef.set(evt.getAdhesive()));
        form.save.click();
        Adhesive savedAdhesive= savedAdhesiveRef.get();

        Assert.assertEquals("Loctite 290", savedAdhesive.getProductName());
        Assert.assertEquals("987789",savedAdhesive.getIdhNumber());
        Assert.assertEquals("142",savedAdhesive.getAmountOfProduct());
        Assert.assertEquals(LocalDate.of(2021,2,21),savedAdhesive.getExpiryDate());
        Assert.assertEquals(LocalDate.of(2021,1,21),savedAdhesive.getRemindDate());
    }






}


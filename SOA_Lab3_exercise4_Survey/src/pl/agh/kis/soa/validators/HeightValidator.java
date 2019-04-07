package pl.agh.kis.soa.validators;

import pl.agh.kis.soa.Survey;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.activation.registries.LogSupport.log;

@FacesValidator("heightValidator")
public class HeightValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        log("Validating submitted email -- " + value.toString());
        Object genderObj = uiComponent.getAttributes().get("gender");
        if (genderObj == null)
            return;

        int height = (int) value;
        String gender = genderObj.toString();
        if (gender.equals("Male") && (height < 165 || height > 200))
        {
            FacesMessage msg =
                    new FacesMessage("Height validation error",
                            "Allowed height values for men: 165 - 200");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

        if (gender.equals("Female") && (height < 150 || height > 185))
        {
            FacesMessage msg =
                    new FacesMessage("Height validation error",
                            "Allowed height values for women: 150 - 185");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}

package pl.agh.kis.soa.validators;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.activation.registries.LogSupport.log;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log("Validating submitted email -- " + value.toString());
        Pattern p = Pattern.compile("^.+@.+\\..+$");
        Matcher m = p.matcher(value.toString());

        if (!m.matches()) {
            FacesMessage msg =
                    new FacesMessage(" E-mail validation failed.",
                            "Please provide E-mail address in this format: abcd@abc.com");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);

            throw new ValidatorException(msg);
        }
    }
}
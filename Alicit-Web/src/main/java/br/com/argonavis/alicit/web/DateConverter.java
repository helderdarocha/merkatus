/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.argonavis.alicit.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("DateConverter")
public class DateConverter implements Converter {

    private final DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            Date date = formatter.parse(value);
            return (Long)date.getTime();
        } catch (ParseException e) {
            throw new ConverterException(e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Date date = new Date((Long)value);
        return formatter.format(date);
    }
    
}

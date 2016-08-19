package com.knight.estoque.adaptadores;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Classe adaptadora que faz a tradução de XMLGregorianCalendar para Date e
 * vice-versa
 * 
 * @author vagner
 * 
 */
public class AdaptadorDate extends XmlAdapter<XMLGregorianCalendar, Date> {

	@Override
	public Date unmarshal(XMLGregorianCalendar xmlGregorianCalendar) throws Exception {
		Date date = xmlGregorianCalendar.toGregorianCalendar().getTime();
		return date;
	}

	@Override
	public XMLGregorianCalendar marshal(Date date) throws Exception {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(date);
		
		XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		xmlGregorianCalendar.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
		xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		
		return xmlGregorianCalendar;
	}

}

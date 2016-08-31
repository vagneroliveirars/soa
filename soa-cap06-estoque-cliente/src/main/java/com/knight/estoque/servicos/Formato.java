
package com.knight.estoque.servicos;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for formato.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="formato">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="pdf"/>
 *     &lt;enumeration value="mobi"/>
 *     &lt;enumeration value="epub"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "formato")
@XmlEnum
public enum Formato {

    @XmlEnumValue("pdf")
    PDF("pdf"),
    @XmlEnumValue("mobi")
    MOBI("mobi"),
    @XmlEnumValue("epub")
    EPUB("epub");
    private final String value;

    Formato(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Formato fromValue(String v) {
        for (Formato c: Formato.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

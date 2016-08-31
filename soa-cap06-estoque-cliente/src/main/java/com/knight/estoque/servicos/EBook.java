
package com.knight.estoque.servicos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eBook complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eBook">
 *   &lt;complexContent>
 *     &lt;extension base="{http://servicos.estoque.knight.com/}livro">
 *       &lt;sequence>
 *         &lt;element name="formato" type="{http://servicos.estoque.knight.com/}formato" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eBook", propOrder = {
    "formato"
})
public class EBook
    extends Livro
{

    protected Formato formato;

    /**
     * Gets the value of the formato property.
     * 
     * @return
     *     possible object is
     *     {@link Formato }
     *     
     */
    public Formato getFormato() {
        return formato;
    }

    /**
     * Sets the value of the formato property.
     * 
     * @param value
     *     allowed object is
     *     {@link Formato }
     *     
     */
    public void setFormato(Formato value) {
        this.formato = value;
    }

}

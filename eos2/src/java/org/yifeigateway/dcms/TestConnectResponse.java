
package org.yifeigateway.dcms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TestConnectResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "testConnectResult"
})
@XmlRootElement(name = "TestConnectResponse")
public class TestConnectResponse {

    @XmlElement(name = "TestConnectResult")
    protected String testConnectResult;

    /**
     * Gets the value of the testConnectResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestConnectResult() {
        return testConnectResult;
    }

    /**
     * Sets the value of the testConnectResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestConnectResult(String value) {
        this.testConnectResult = value;
    }

}

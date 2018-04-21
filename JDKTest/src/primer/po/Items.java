//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.11 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.11.03 时间 10:38:43 PM CST 
//


package primer.po;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Items complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="Items"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="item" maxOccurs="unbounded"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="quantity"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                         &lt;maxExclusive value="100"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="USPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *                   &lt;element ref="{}comment" minOccurs="0"/&gt;
 *                   &lt;element name="shipDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="partNum" use="required" type="{}SKU" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Items", propOrder = {
    "item"
})
public class Items {

    @XmlElement(required = true)
    protected List<Items.Item> item;

    /**
     * Gets the value of the item property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the item property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Items.Item }
     * 
     * 
     */
    public List<Items.Item> getItem() {
        if (item == null) {
            item = new ArrayList<Items.Item>();
        }
        return this.item;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="quantity"&gt;
     *           &lt;simpleType&gt;
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
     *               &lt;maxExclusive value="100"/&gt;
     *             &lt;/restriction&gt;
     *           &lt;/simpleType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="USPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
     *         &lt;element ref="{}comment" minOccurs="0"/&gt;
     *         &lt;element name="shipDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="partNum" use="required" type="{}SKU" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "productName",
        "quantity",
        "usPrice",
        "comment",
        "shipDate"
    })
    public static class Item {

        @XmlElement(required = true)
        protected String productName;
        protected int quantity;
        @XmlElement(name = "USPrice", required = true)
        protected BigDecimal usPrice;
        protected String comment;
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar shipDate;
        @XmlAttribute(name = "partNum", required = true)
        protected String partNum;

        /**
         * 获取productName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductName() {
            return productName;
        }

        /**
         * 设置productName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductName(String value) {
            this.productName = value;
        }

        /**
         * 获取quantity属性的值。
         * 
         */
        public int getQuantity() {
            return quantity;
        }

        /**
         * 设置quantity属性的值。
         * 
         */
        public void setQuantity(int value) {
            this.quantity = value;
        }

        /**
         * 获取usPrice属性的值。
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUSPrice() {
            return usPrice;
        }

        /**
         * 设置usPrice属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUSPrice(BigDecimal value) {
            this.usPrice = value;
        }

        /**
         * 获取comment属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getComment() {
            return comment;
        }

        /**
         * 设置comment属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setComment(String value) {
            this.comment = value;
        }

        /**
         * 获取shipDate属性的值。
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getShipDate() {
            return shipDate;
        }

        /**
         * 设置shipDate属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setShipDate(XMLGregorianCalendar value) {
            this.shipDate = value;
        }

        /**
         * 获取partNum属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPartNum() {
            return partNum;
        }

        /**
         * 设置partNum属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPartNum(String value) {
            this.partNum = value;
        }

    }

}

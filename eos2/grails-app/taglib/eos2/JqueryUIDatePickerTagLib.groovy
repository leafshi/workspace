package eos2

class JqueryUIDatePickerTagLib {
    def jqDatePicker = {attrs, body ->
        def out = out
        def name = attrs.name
        def id = attrs.id?:name
        def value = attrs.value?:new Date()
        //created date text field and supporting hidden text fields need by grails
        out.println "<input type=\"hidden\" name=\"${name}\" value=\"date.struct\" />"
        out.println "<input type=\"text\" value=\"${g.formatDate(format:'yyyy-MM-dd', date:value)}\" datepicker=\"datepicker\" target=\"${name}\" style=\"width:80px;padding-right : 0px;\"/>"
        out.println "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" value=\"${g.formatDate(format:'dd', date:value)}\"/>"
        out.println "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" value=\"${g.formatDate(format:'MM', date:value)}\"/>"
        out.println "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" value=\"${g.formatDate(format:'yyyy', date:value)}\"/>"
    }
}

<#--
START SNIPPET: supported-validators
Only the following validators are supported:
* required validator
* requiredstring validator
* stringlength validator
* regex validator
* email validator
* url validator
* int validator
* double validator
END SNIPPET: supported-validators
-->
<#if ((parameters.validate?default(false) == true) && (parameters.performValidation?default(false) == true))>
<script type="text/javascript">

	  var errorMessages = "";
      function addError(field,error){
					if(errorMessages)
							errorMessages+="\n"+error;
					else
							errorMessages+=error;
			}



    function validateForm_${parameters.id}() {
        form = document.getElementById("${parameters.id}");
        clearErrorMessages(form);
        clearErrorLabels(form);
         errorsMessage="";

        var errors = false;
    <#list parameters.tagNames as tagName>
        <#list tag.getValidators("${tagName}") as validator>
        if (form.elements['${validator.fieldName}']) {
            field = form.elements['${validator.fieldName}'];
            var error = "${validator.getMessage(action)?js_string}";
            <#if validator.validatorType = "required">
            if (field.value == "") {
                addError(field, error);
                errors = true;
            }
            <#elseif validator.validatorType = "requiredstring">
            if (field.value != null && (field.value == "" || field.value.replace(/^\s+|\s+$/g,"").length == 0)) {
                addError(field, error);
                errors = true;
            }
            <#elseif validator.validatorType = "stringlength">
            if (field.value != null) {
                var value = field.value;
                <#if validator.trim>
                    //trim field value
                    while (value.substring(0,1) == ' ')
                        value = value.substring(1, value.length);
                    while (value.substring(value.length-1, value.length) == ' ')
                        value = value.substring(0, value.length-1);
                </#if>
                if((${validator.minLength} > -1 && value.length < ${validator.minLength}) ||
                        (${validator.maxLength} > -1 && value.length > ${validator.maxLength})) {
                    addError(field, error);
                    errors = true;
                }
            }
            <#elseif validator.validatorType = "regex">
            if (field.value != null && !field.value.match("${validator.expression?js_string}")) {
                addError(field, error);
                errors = true;
            }
            <#elseif validator.validatorType = "email">
            if (field.value != null && field.value.length > 0 && field.value.match(/\b(^[_A-Za-z0-9-]+(\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))$)\b/gi)==null) {
                addError(field, error);
                errors = true;
            }
            <#elseif validator.validatorType = "url">
            if (field.value != null && field.value.length > 0 && field.value.match(/(^(ftp|http|https):\/\/(\.[_A-Za-z0-9-]+)*(@?([A-Za-z0-9-])+)?(\.[A-Za-z0-9-]+)*((\.[A-Za-z0-9]{2,})|(\.[A-Za-z0-9]{2,}\.[A-Za-z0-9]{2,}))(:[0-9]+)?([/A-Za-z0-9?#_-]*)?$)/gi)==null) { 
                addError(field, error);
                errors = true;
            }
            <#elseif validator.validatorType = "int">
            if (field.value != null) {
                if (<#if validator.min?exists>parseInt(field.value) <
                     ${validator.min}<#else>false</#if> ||
                        <#if validator.max?exists>parseInt(field.value) >
                           ${validator.max}<#else>false</#if>) {
                    addError(field, error);
                    errors = true;
                }
            }
            <#elseif validator.validatorType = "double">
            if (field.value != null) {
                var value = parseFloat(field.value);
                if (<#if validator.minInclusive?exists>value < ${validator.minInclusive}<#else>false</#if> ||
                        <#if validator.maxInclusive?exists>value > ${validator.maxInclusive}<#else>false</#if> ||
                        <#if validator.minExclusive?exists>value <= ${validator.minExclusive}<#else>false</#if> ||
                        <#if validator.maxExclusive?exists>value >= ${validator.maxExclusive}<#else>false</#if>) {
                    addError(field, error);
                    errors = true;
                }
            }
            </#if>
        }
        </#list>
    </#list>
        if(errors){
           alert(errorMessages);  
           errorMessages="";  
          }  
        return !errors;  

    }
</script>
</#if>

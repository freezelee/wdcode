<#if parameters.validate?default(false) == true>

	<script type="text/javascript" src="${base}/struts/xhtml/validation.js"></script>
	<script type="text/javascript" src="${base}/struts/utils.js"></script>
	<#if parameters.onsubmit?exists>
		${tag.addParameter('onsubmit', "${parameters.onsubmit}; return validateForm_${parameters.id}();")}
	<#else>
		${tag.addParameter('onsubmit', "return validateForm_${parameters.id}();")}
	</#if>
</#if>


<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>${ not empty title ? title : 'e-commerce cart ' }</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="${initParam.FRONT_OFFICE_ASSETS }assets/favicon.ico" />
<!-- Bootstrap icons-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="${initParam.FRONT_OFFICE_ASSETS }css/styles.css" rel="stylesheet" />
<c:if test="${cart_css == true }">
	<link href="${initParam.FRONT_OFFICE_ASSETS }css/cart.css" rel="stylesheet" />
</c:if>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
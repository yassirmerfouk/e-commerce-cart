<%@ include file="../includes/front-office/part1.jsp"%>
<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
	<c:forEach items="${products }" var="product">
		<div class="col mb-5">
			<div class="card h-100">
				<!-- Sale badge-->
				<div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
				<!-- Product image-->
				<div class="image-card">
					<c:choose>
						<c:when test="${not empty product.image }">
							<img class="card-img-top" src="<%= application.getInitParameter("PRODUCTS_IMAGES") %>${product.image }" alt="..." />
						</c:when>
						<c:otherwise>
							<img class="card-img-top" src="https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/1665px-No-Image-Placeholder.svg.png" alt="..." />
						</c:otherwise>
					</c:choose>
				</div>
				<!-- Product details-->
				<div class="card-body p-4">
					<div class="text-center">
						<!-- Product name-->
						<h5 class="fw-bolder">${product.name }</h5>
						<!-- Product reviews-->
						<div class="d-flex justify-content-center small text-warning mb-2">
							<div class="bi-star-fill"></div>
							<div class="bi-star-fill"></div>
							<div class="bi-star-fill"></div>
							<div class="bi-star-fill"></div>
							<div class="bi-star-fill"></div>
						</div>
						<!-- Product price-->
						<span class="text-muted text-decoration-line-through">$${product.regularPrice }</span>
						$${product.getSalePrice()}
					</div>
				</div>
				<!-- Product actions-->
				<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
					<form action="addcartproduct?productid=${product.id }" method="POST">
						<div class="text-center">
							<input type="submit" name="add" value="Add to cart" class="btn btn-outline-dark mt-auto" href="#">
						</div>
					</form>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
<c:if test="${not empty add_success }">
	<script>
		Swal.fire({
			position : 'top-end',
			icon : 'success',
			title : 'The product has been added to your cart',
			showConfirmButton : true,
			timer : 4000
		})
	</script>
	<c:remove var="add_success" scope="session" />
</c:if>
<c:if test="${not empty add_error }">
	<script>
		Swal.fire({
			position : 'center',
			icon : 'error',
			title : 'The product selectd is arleady exists',
			showConfirmButton : true,
			timer : 4000
		})
	</script>
	<c:remove var="add_error" scope="session" />
</c:if>
<%@ include file="../includes/front-office/part2.jsp"%>
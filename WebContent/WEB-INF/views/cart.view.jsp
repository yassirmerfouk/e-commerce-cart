<%@ include file="../includes/front-office/part1.jsp"%>
<c:choose>
	<c:when test="${cart.isEmpty() }">
		<div class="alert alert-warning" role="alert">
			<h4 class="alert-heading">your cart is currently empty</h4>
			<hr>
			<p class="mb-0"></p>
		</div>
	</c:when>
	<c:otherwise>
		<div class="container-fluid">
			<div class="row">
				<aside class="col-lg-9">
					<div class="card p-2">
						<div class="table-responsive">
							<table class="table table-borderless table-shopping-cart">
								<thead class="text-muted">
									<tr class="small text-uppercase">
										<th scope="col">Product</th>
										<th scope="col" width="120">Quantity</th>
										<th scope="col" width="120">Price</th>
										<th scope="col" class="text-right d-none d-md-block" width="200"></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cart.items }" var="item">
										<tr>
											<td>
												<figure class="itemside align-items-center">
													<div class="aside">
														<c:choose>
															<c:when test="${not empty item.product.image }">
																	<img src="<%= application.getInitParameter("PRODUCTS_IMAGES") %>${item.product.image}" class="img-sm">
															</c:when>
															<c:otherwise>
																<img src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" class="img-sm">
															</c:otherwise>
														</c:choose>
													</div>
													<figcaption class="info" style="align-self: flex-start;">
														<a href="#" class="title text-dark" data-abc="true">${item.product.name }</a>
													</figcaption>
												</figure>
											</td>
											<form action="updatecartproduct?id=${item.product.id }" method="POST">
												<td>
													<select name="qte" class="form-control">
														<option value="${item.qte }">${item.qte }</option>
														<c:forEach var="number" begin="1" end="10">
															<c:if test="${item.qte != number }">
																<option value="${number }">${number }</option>
															</c:if>
														</c:forEach>
													</select>
												</td>
												<td>
													<div class="price-wrap">
														<var class="price">$${item.calculatePrice() }</var>
														<small class="text-muted"> $${item.product.salePrice } </small>
													</div>
												</td>
												<td class="text-right d-none d-md-block">
													<div class="d-flex">
														<button type="submit" class="btn btn-success" data-abc="true">
															<i class="fas fa-edit"></i>
															Update
														</button>
											</form>
											<form action="deletecartproduct?id=${item.product.id }" method="POST">
												<button href="" class="btn btn-light" data-abc="true" onclick="myFunction()">
													<i class="fas fa-trash"></i>
													Remove
												</button>
											</form>
											</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</aside>
				<aside class="col-lg-3">
					<div class="card">
						<div class="card-body">
							<dl class="dlist-align">
								<dt>Total price:</dt>
								<dd class="text-right ml-3">&nbsp; $${cart.calculateTotal() }</dd>
							</dl>
							<dl class="dlist-align">
								<dt>Discount:</dt>
								<dd class="text-right text-danger ml-3">&nbsp; - $10.00</dd>
							</dl>
							<dl class="dlist-align">
								<dt>Total:</dt>
								<dd class="text-right text-dark b ml-3">
									<strong>&nbsp; $59.97</strong>
								</dd>
							</dl>
							<hr>
							<form action="clearcart" method="POST">
								<button type="submit" class="btn btn-out btn-danger btn-square btn-main" data-abc="true" onclick="myFunction()">Remove All</button>
							</form>
							<a href="#" class="btn btn-out btn-success btn-square btn-main mt-2" data-abc="true">Continue Shopping</a>
						</div>
					</div>
				</aside>
			</div>
		</div>
	</c:otherwise>
</c:choose>
<%@ include file="../includes/front-office/part2.jsp"%>
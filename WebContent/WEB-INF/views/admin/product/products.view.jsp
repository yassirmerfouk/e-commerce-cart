<%@ include file="../../../includes/back-office/part1.jsp"%>

<div class="col-lg-12">
	<div class="card spur-card">
		<div class="card-header">
			<div class="spur-card-icon">
				<i class="fas fa-table"></i>
			</div>
			<div class="spur-card-title">Products</div>
		</div>
		<div class="card-body ">
			<c:if test="${!empty add_success }">
				<div class="alert alert-success" role="alert">${add_success }</div>
			</c:if>
			<c:remove var="add_success" />
			<c:if test="${!empty update_success }">
				<div class="alert alert-success" role="alert">${update_success }</div>
			</c:if>
			<c:remove var="update_success" />
			<c:if test="${!empty delete_success }">
				<div class="alert alert-success" role="alert">${delete_success }</div>
			</c:if>
			<c:remove var="delete_success" />
			<table class="table table-striped table-in-card">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Name</th>
						<th scope="col">Regular Price</th>
						<th scope="col">Sale Price</th>
						<th scope="col">Discount</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${products}" var="product">
						<tr>
							<th scope="row">${product.id }</th>
							<td>${product.name }</td>
							<td>${product.regularPrice }</td>
							<td>${product.getSalePrice() }</td>
							<td>${product.getDiscountPercentage() }%</td>
							<td>
								<div class="d-flex">
									<a type="submit" class="btn btn-success btn-sm mr-1" href="../admin/editproduct?id=${product.id }">
										<i class="fas fa-edit"></i>
										Update
									</a>
									<form action="../admin/deleteproduct?id=${product.id }" method="POST">
										<button type="submit" class="btn btn-danger btn-sm" onclick="myFunction()">
											<i class="fas fa-trash"></i>
											Delete
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
</div>

<%@ include file="../../../includes/back-office/part2.jsp"%>
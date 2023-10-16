<%@ include file="../../../includes/back-office/part1.jsp"%>

<div class="col-lg-12">
	<div class="card spur-card">
		<div class="card-header">
			<div class="spur-card-icon">
				<i class="fas fa-table"></i>
			</div>
			<div class="spur-card-title">Add new categoty</div>
		</div>
		<div class="card-body ">
			<c:if test="${!empty add_success }">
				<div class="alert alert-success" role="alert">${add_success }</div>
			</c:if>
			<c:remove var="add_success" />
			<form action="../admin/addcategory" method="post">
				<div class="form-row">
					<div class="form-group col-md-9">
						<label for="name">Category name *</label>
						<input type="text" class="form-control" id="name" name="name" placeholder="Category name">
						<c:if test="${!empty error_name }">
							<p class="text-danger mt-1 mb-0">${error_name }</p>
						</c:if>
						<c:remove var="error_name" />
					</div>
					<div class="form-group col-md-3">
						<label class=""></label>
						<input type="submit" name="add" value="Add category" class="form-control btn btn-primary mt-2 mb-1">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="col-lg-12">
	<div class="card spur-card">
		<div class="card-header">
			<div class="spur-card-icon">
				<i class="fas fa-table"></i>
			</div>
			<div class="spur-card-title">Categories</div>
		</div>
		<div class="card-body ">
			<c:if test="${!empty delete_success }">
				<div class="alert alert-success" role="alert">${delete_success }</div>
			</c:if>
			<c:remove var="delete_success" />
			<table class="table table-striped table-in-card">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Name</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categories}" var="category">
						<tr>
							<th scope="row">${category.id }</th>
							<td>${category.name }</td>
							<td>
								<form action="../admin/deletecategory?id=${category.id }" method="POST">
									<button type="submit" class="btn btn-danger btn-sm" onclick="myFunction()">
										<i class="fas fa-trash"></i>
										Delete
									</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<%@ include file="../../../includes/back-office/part2.jsp"%>
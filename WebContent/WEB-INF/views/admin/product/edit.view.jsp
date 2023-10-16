<%@ include file="../../../includes/back-office/part1.jsp"%>

<div class="col-xl-12">
	<div class="card spur-card">
		<div class="card-header">
			<div class="spur-card-icon">
				<i class="fas fa-chart-bar"></i>
			</div>
			<div class="spur-card-title">Complex layout</div>
		</div>
		<div class="card-body ">
			<form action="../admin/updateproduct?id=${product.id }" method="POST" enctype="multipart/form-data">
				<div class="form-group">
					<label for="name">Product name</label>
					<input type="text" name="name" class="form-control" id="name" placeholder="Product name" value="${product.name}">
					<c:if test="${!empty error_name }">
						<p class="text-danger mt-1 mb-0">${error_name }</p>
					</c:if>
					<c:remove var="error_name" />
				</div>
				<div class="form-group">
					<label for="description">Product description</label>
					<textarea name="description" class="form-control" id="description">${product.description }</textarea>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="regular_price">Regular price</label>
						<input type="number" name="regular_price" step="0.1" class="form-control" id="regular_price" placeholder="Number"
							value="${product.regularPrice}">
						<c:if test="${!empty error_regularPrice }">
							<p class="text-danger mt-1 mb-0">${error_regularPrice }</p>
						</c:if>
						<c:remove var="error_regularPrice" />
					</div>
					<div class="form-group col-md-6">
						<label for="discount">Discount</label>
						<input type="number" name="discount" step="0.1" min="0" max="100" class="form-control" id="discount" placeholder="Discount"
							value="${product.getDiscountPercentage()}">
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="exampleFormControlFile1">Product category</label>
						<select id="category" name="category_id" class="form-control">
						<option value="${product.category.id}" selected">${product.category.name }</option>
							<c:forEach items="${categories }" var="category">
								<c:if test="${product.category.id != category.id }">
								<option value="${category.id }">${category.name }</option>
								</c:if>
							</c:forEach>
						</select>
						<c:if test="${!empty error_categoryId }">
							<p class="text-danger mt-1 mb-0">${error_categoryId }</p>
						</c:if>
						<c:remove var="error_categoryId" />
					</div>
					<div class="form-group col-md-6">
						<label for="exampleFormControlFile1">Product image</label>
						<input type="file" name="image" class="form-control pt-1" id="exampleFormControlFile1">
					</div>
					<input type="submit" name="add" value="Update product" class="btn btn-primary mt-2">
			</form>
		</div>
	</div>
</div>

<%@ include file="../../../includes/back-office/part2.jsp"%>
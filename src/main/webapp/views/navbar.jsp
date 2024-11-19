<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<nav class="navbar navbar-expand-lg navbar-dark bg-success">
	<a class="navbar-brand" href="index.jsp"><i
		class="fa-solid fa-cart-shopping"></i>ECOME STORE</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav ml-auto">
		<c:choose>
		<c:when test="${user==null }">
			<li class="nav-item active"><a class="nav-link" href="/"><i
					class="fa-solid fa-house"></i>HOME <span class="sr-only">(current)</span>
			</a></li>
			</c:when>
			<c:otherwise>
			<c:if test="${user.role=='ROLE_ADMIN' }">
			<li class="nav-item active"><a class="nav-link" href="/admin/"><i
					class="fa-solid fa-house"></i>HOME <span class="sr-only">(current)</span>
			</a></li>
			</c:if>
			<c:if test="${user.role=='ROLE_USER' }">
			<li class="nav-item active"><a class="nav-link" href="/"><i
					class="fa-solid fa-house"></i>HOME <span class="sr-only">(current)</span>
			</a></li>
			</c:if>
			</c:otherwise>
</c:choose>

			<li class="nav-item"><a class="nav-link" href="/products">PRODUCTS</a></li>


			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Category </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<c:forEach var="c" items="${categ}">
						<c:url var="categoryURL" value="/products">
							<c:param name="category" value="${c.name}" />
						</c:url>
						<a class="dropdown-item" href="${categoryURL}">${c.name}</a>
					</c:forEach>
				</div></li>

		</ul>
		<ul class="navbar-nav ms-auto">
			<c:if test="${user == null }">
				<li class="nav-item active"><a class="nav-link" href="/signin"><i
						class="fa-solid fa-right-to-bracket"></i>login <span
						class="sr-only">(current)</span> </a></li>


				<li class="nav-item"><a class="nav-link" href="/register">Register</a></li>
				
			</c:if>
			<c:if test="${user != null }">
			<c:if test="${user.role=='ROLE_USER' }">
				<li class="nav-item"><a class="nav-link" href="/user/cart"><i
						class="fa-solid fa-cart-flatbed-suitcase"></i>${countCart} cart</a></li>
						</c:if>
						
				<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle active" href="#" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <i
								class="fa-solid fa-user"></i> ${user.name}
						</a>
						<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="/user/profiles">Profile</a></li>
								<c:if test="${user.role=='ROLE_USER' }">
								<li><a class="dropdown-item" href="/user/user-orders">My Orders</a></li>
								</c:if>
								<li class="dropdown-item"><a class="dropdown-item" href="/logout">Logout</a></li>
								<li><hr class="dropdown-divider"></li>

							</ul>
						</li>
			</c:if>





		</ul>


	</div>
</nav>
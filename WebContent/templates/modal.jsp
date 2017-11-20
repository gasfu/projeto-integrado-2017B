<div class="modal-wrapper hide" app-location-info>
	<div class="modal">
		<header class="header">
			<figure class="avatar">
				<img class="image" src="https://api.adorable.io/avatars/1" alt="avatar">
			</figure>
			<div class="description">
				<h2 class="title" info-local-name>Pq. Ibirapuera</h2>
				<p class="by">por <a href="#" info-user-username>username</a> em <label info-local-create-at>Out 12, 2017</label>  </p>
			</div>
		</header>
		<div class="map" info-map></div>
		<div class="description" info-local-description>
			<p class="about">Mussum Ipsum, cacilds vidis litro abertis. Nec orci ornare consequat. Praesent lacinia ultrices consectetur. Sed non ipsum felis. Atirei o pau no gatis, per gatis num morreus. Suco de cevadiss deixa as pessoas mais interessantis. Si num tem leite então bota uma pinga aí cumpadi!</p>
			<ul class="options">
				<li class="item"><img src="/acesse/public/icons/map-pin.svg"/>Location, SP</li>
				<li class="item"><img src="/acesse/public/icons/storage.svg"/>Categoria</li>
			</ul>
		</div>
		<div class="evaluations">
			<h3 class="title">Avaliações</h3>
			
			<ul class="list" app-evaluation-list>
				<li class="item">
					<div class="figure-wrapper">
						<figure class="avatar">
							<img class="image" src="https://api.adorable.io/avatars/12" alt="avatar">
						</figure>
					</div>
					<main class="main"> 
						<h4 class="name">Mariana Ramos <a href="#" class="username">mari.ramos</a></h4>
						<p class="comment">Atirei o pau no gatis, per gatis num morreus. Suco de cevadiss deixa as pessoas mais interessantis.</p>
						<div class="data">
							<span class="create_at">2 dias atrás</span>
						</div>
					</main>
				</li>
				
				<li class="item">
					<div class="figure-wrapper">
						<figure class="avatar">
							<img class="image" src="https://api.adorable.io/avatars/12" alt="avatar">
						</figure>
					</div>
					<main class="main"> 
						<h4 class="name">Mariana Ramos <a href="#" class="username">mari.ramos</a></h4>
						<p class="comment">Atirei o pau no gatis, per gatis num morreus. Suco de cevadiss deixa as pessoas mais interessantis.</p>
						<div class="data">
							<span class="create_at">2 dias atrás</span>
						</div>
					</main>
				</li>
				
			</ul>
			
			<form class="form evalue" app-register-evaluation-action>
				<h4 class="title">Deixe sua avaliação, contribuindo para um mundo mais acessivel :)</h4>
				<div class="stars">
					<input class="radio" type="radio" name="VALUE" id="value1" value="1" />
					<label class="star _10 interior" for="value1"></label>
					<input class="radio" type="radio" name="VALUE" id="value2" value="2" />
					<label class="star _10 interior" for="value2"></label>
					<input class="radio" type="radio" name="VALUE" id="value3" checked value="3" />
					<label class="star _10 interior" for="value3"></label>
					<input class="radio" type="radio" name="VALUE" id="value4" value="4" />
					<label class="star _10 interior" for="value4"></label>
					<input class="radio" type="radio" name="VALUE" id="value5" value="5" />
					<label class="star _10 interior" for="value5"></label>
				</div>
				<input type="hidden" name="LOCAL_ID" info-local-id>
				<textarea class="text-area" name="COMMENT" placeholder="comentário"></textarea>
				<button class="button" type="submit">Avaliar</button>
			</form>
			
		</div>
	</div>
</div>
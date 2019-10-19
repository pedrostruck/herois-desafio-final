moduleApp.factory("batalhaService", function ($http) {

	const baseUrl = 'http://localhost:8080/curso-hackaton-cdi/batalha';

	const _batalharVsJogador = function (jogador,jogador2) {
		return $http.post(baseUrl,jogador,jogador2);
	};

	return {
		batalharVsJogador: _batalharVsJogador
	};	
});
moduleApp.factory("batalhaService", function ($http) {

	const baseUrl = 'http://localhost:8080/curso-hackaton-cdi/batalha';

	const getOponentesUrl = 'http://localhost:8080/curso-hackaton-cdi/batalha/getOponentes';

	const _batalharVsJogador = function (jogador, jogador2) {
		return $http.post(baseUrl, jogador, jogador2);
	};

	const _getOponentes = function(jogadorLogado) {
		return $http.post(getOponentesUrl, jogadorLogado);
	};

	return {
		batalharVsJogador: _batalharVsJogador,
		getOponentes: _getOponentes
	};

});
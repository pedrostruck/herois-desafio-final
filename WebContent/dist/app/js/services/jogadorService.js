moduleApp.factory("jogadorService", function ($http) {

	const inserirUrl = 'http://localhost:8080/curso-hackaton-cdi/jogador/';
    const loginUrl = 'http://localhost:8080/curso-hackaton-cdi/jogador/autenticar';
    
    const self = this;
    
    this.jogador = {
        nickname: null,
        senha: null,
        heroi: {}
    };

	function _inserirJogador(jogador) {
        return $http.post(inserirUrl, jogador);
    };

    function _login(jogador) {
        return $http.post(loginUrl,jogador);
    };

	return {
        inserirJogador: _inserirJogador,
        login: _login
	};	
});
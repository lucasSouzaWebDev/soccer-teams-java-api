# Soccer teams API

## Rotas
    
    * GET '/teams' : retorna todos os recursos, possui os parametros:
        String name: filtro do tipo like;
        int size: quantidade de itens que serão retornados pela paginação;
        int page: define o número da página que deve ser retornada.
    
    * GET '/teams/{id}': retorna um recurso específico;

    * POST '/teams/': cria um novo recurso;

    * PUT '/teams/{id}': atualiza um determinado recurso;

    * DELETE '/teams/{id}': exclui um determinado recurso;
         
package redsi.form1;

/**
 * @author Misterio
 */
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystemOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import static io.vertx.ext.web.handler.StaticHandler.DEFAULT_WEB_ROOT;

public class Servidor extends AbstractVerticle {

    private String webRoot = DEFAULT_WEB_ROOT;

    @Override
    public void start(Promise<Void> promise) throws Exception {
     
        Router router = Router.router(vertx);

        // por pré-definição serve index.html
        router.route(HttpMethod.GET, "/*").handler(StaticHandler.create(webRoot));

        router.route().handler(BodyHandler.create());
     
        router.route(HttpMethod.POST, "/alunos").handler(this::addAluno);
        
        // pedido de recurso estático (página nova)
        // router.route(HttpMethod.GET,"/alunos").handler(StaticHandler.create(webRoot+"/"+"folha2.html"));       

        // cria servidor HTTP     
        HttpServerOptions options = new HttpServerOptions();
        options.setPort(8000);
        vertx.createHttpServer(options)
                .requestHandler(router)
                .listen(res -> {
                    if (res.succeeded()) {
                        promise.complete();
                    } else {
                        promise.fail(res.cause());
                    }
                });

    }

    @Override
    public void stop() {
        System.out.println("---> REDSI stop! ");
    }

    public static void main(String[] args) {
        FileSystemOptions fsOptions = new FileSystemOptions();
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Servidor());
    }

    private void addAluno(RoutingContext rc) {
        System.out.println("Entrou no método addAlunos");
        String name = rc.request().getParam("nome");
        String email = rc.request().getParam("email");
        System.out.println(name +" nome " + email + " Email ");
        //Apenas para exemplificar
        Aluno aluno = new Aluno(name, email, 1);
        HttpServerResponse response = rc.response();
        final String json = Json.encodePrettily(aluno);
        response.setStatusCode(200) .putHeader("content-type", "application/json; charset=utf-8").end(json);
    }
}

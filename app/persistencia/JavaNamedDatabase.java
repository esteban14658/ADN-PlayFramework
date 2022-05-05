package persistencia;

import play.db.Database;
import play.db.NamedDatabase;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class JavaNamedDatabase {

    private final Database db;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JavaNamedDatabase(
            @NamedDatabase("jugadores") Database db, DatabaseExecutionContext executionContext){
        this.db = db;
        this.executionContext = executionContext;
    }
}

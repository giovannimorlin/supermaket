* add schema to db tables
* use stream(akka/fs2) to return result from doobie
* gestione errori
  *  nel caso in cui uso un tipo non corretto nella sringa id=[String] il parsing fallisce ma l'errore non viene gestisto. Dovrei gestirlo e notificarlo

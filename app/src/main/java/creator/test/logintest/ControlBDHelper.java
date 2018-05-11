package creator.test.logintest;
/**
 * Created by Mego on 5/10/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Created by Mego on 5/7/2018.
 */

public class ControlBDHelper {

    private static final String[] camposDocente = new String[]{"nombdocente", "apelldocente", "correo", "direccion"};
    private static final String[] camposAreaEvaluacion = new String[]{"idoferta","tipoarea"};
    private static final String[] camposMateria = new String[]{"codmateria","unidadval", "nombremat"};
    private static final String[] camposCiclo = new String[]{"numciclo", "aniociclo", "fechaini", "fechafin"};
    private static final String[] camposCuestionario = new String[]{"descricuestionario", "fechaexamen", "duracion", "ponderacion"};
    private static final String[] camposDetalleEstudiante = new String[]{"carnet","idoferta"};
    private static final String[] camposDetallePregunta = new String[]{"idcuestionario", "idpregunta"};
    private static final String[] camposEstudiante = new String[]{"carnet","nombreestu","apellidoestu","correoestu","direccionestu"};
    private static final String[] camposOfertaAcademica = new String[]{"idmateria","iddocente","idciclo","descripcion"};
    private static final String[] camposOpcion = new String[]{"idpregunta", "descripopc","esCorrecta"};
    private static final String[] camposPregunta = new String[]{"idarea","poderapregunt","descrippreg","tipopreg"};
    private static final String[] camposRespuesta = new String[]{"iddetallepreg", "carnet"};
    private final Context context;
    public DatabaseHelper DBHelper;
    public SQLiteDatabase db;
    private static final String DROP_TABLE1 = "DROP TABLE IF EXISTS docente; ";
    private static final String DROP_TABLE2 = "DROP TABLE IF EXISTS materia; ";
    private static final String DROP_TABLE3 = "DROP TABLE IF EXISTS usuario; ";
    private static final String DROP_TABLE4 = "DROP TABLE IF EXISTS accesousuario; ";
    private static final String DROP_TABLE5 = "DROP TABLE IF EXISTS areaEvaluacion; ";
    private static final String DROP_TABLE6 = "DROP TABLE IF EXISTS ciclo; ";
    private static final String DROP_TABLE7 = "DROP TABLE IF EXISTS cuestionario; ";
    private static final String DROP_TABLE8 = "DROP TABLE IF EXISTS detalleEstudiante; ";
    private static final String DROP_TABLE9 = "DROP TABLE IF EXISTS detallePregunta; ";
    private static final String DROP_TABLE10 = "DROP TABLE IF EXISTS estudiante; ";
    private static final String DROP_TABLE11 = "DROP TABLE IF EXISTS ofertaAcademica; ";
    private static final String DROP_TABLE12 = "DROP TABLE IF EXISTS opcion; ";
    private static final String DROP_TABLE13 = "DROP TABLE IF EXISTS opcioncrud; ";
    private static final String DROP_TABLE14 = "DROP TABLE IF EXISTS pregunta; ";
    private static final String DROP_TABLE15 = "DROP TABLE IF EXISTS respuesta; ";

    public ControlBDHelper(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String BASE_DATOS = "appTest.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION
            );
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE [usuario](" +
                        "[nomusuario] VARCHAR(30)  NOT NULL PRIMARY KEY,\n" +
                        "[clave] VARCHAR(30)  NOT NULL," +
                        "[isadmin] BOOLEAN  NOT NULL," +
                        "[isdocente] BOOLEAN NOT NULL," +
                        "[isestudiante] BOOLEAN NOT NULL)");


                db.execSQL("CREATE TABLE accesousuario(\n" +
                        "idopcion VARCHAR(3)NOT NULL,\n" +
                        "nomusuario VARCHAR (30)NOT NULL,\n" +
                        "PRIMARY KEY (idopcion,nomusuario),\n" +
                        "CONSTRAINT fk_nomusuario FOREIGN KEY (nomusuario) REFERENCES usuario(nomusuario) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE areaEvaluacion( idarea INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "idoferta INTEGER, " +
                        "tipoarea VARCHAR(50) NOT NULL," +
                        " CONSTRAINT fk_idoferta FOREIGN KEY (idoferta) REFERENCES ofertaAcademica(idoferta) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE ciclo( " +
                        "idciclo INTEGER NOT NULL PRIMARY KEY, " +
                        "numciclo INTEGER NOT NULL, " +
                        "aniociclo INTEGER NOT NULL, " +
                        "fechaini  TEXT NOT NULL, " +
                        "fechafin TEXT NOT NULL)");

                db.execSQL("CREATE TABLE cuestionario(\n" +
                        "idcuestionario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "idoferta INTEGER,\n" +
                        "descricuestionario VARCHAR(1024),\n" +
                        "fechaexamen TEXT,\n" +
                        "duracion INTEGER,\n" +
                        "ponderacion FLOAT,\n" +
                        "CONSTRAINT fk_idoferta FOREIGN KEY (idoferta) REFERENCES ofertaAcademica(idoferta) ON DELETE RESTRICT)");

                db.execSQL("CREATE TABLE detalleEstudiante(\n" +
                        "iddetalleestu INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "carnet VARCHAR(7),\n" +
                        "idoferta INTEGER,\n" +
                        "CONSTRAINT fk_carnet FOREIGN KEY (carnet) REFERENCES estudiante(carnet) ON DELETE RESTRICT,\n" +
                        "CONSTRAINT fk_idoferta FOREIGN KEY (idoferta) REFERENCES ofertaAcademica(idoferta) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE detallePregunta(\n" +
                        "iddetallepreg INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "idcuestionario INTEGER,\n" +
                        "idpregunta INTEGER,\n" +
                        "CONSTRAINT fk_idpregunta FOREIGN KEY (idpregunta) REFERENCES  pregunta(idpregunta) ON DELETE RESTRICT,\n" +
                        "CONSTRAINT fk_idcuestionario FOREIGN KEY (idcuestionario) REFERENCES cuestionario(idcuestionario) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE docente (\n" +
                        "iddocente INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "nombdocente VARCHAR(50) NOT NULL,\n" +
                        "apelldocente VARCHAR(50) NOT NULL,\n" +
                        "correo VARCHAR(100) NOT NULL,\n" +
                        "direccion VARCHAR (70) NOT NULL,\n" +
                        "nomusuario VARCHAR(30),\n" +
                        "CONSTRAINT fk_nomusuario FOREIGN KEY (nomusuario) REFERENCES usuario(nomusuario) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE estudiante(\n" +
                        "carnet VARCHAR(7) NOT NULL PRIMARY KEY,\n" +
                        "nombreestu VARCHAR(60) NOT NULL,\n" +
                        "apellidoestu VARCHAR(60) NOT NULL,\n" +
                        "correoestu VARCHAR(30) NOT NULL,\n" +
                        "direccionestu VARCHAR(50) NOT NULL,\n" +
                        "nomusuario VARCHAR(30),\n" +
                        "CONSTRAINT fk_nomusuario FOREIGN KEY (nomusuario) REFERENCES usuario(nomusuario) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE materia (\n" +
                        "idmateria INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "codmateria VARCHAR(10) NOT NULL,\n" +
                        "unidadval INTEGER NOT NULL,\n" +
                        "nombremat VARCHAR(50) NOT NULL)");


                db.execSQL("CREATE TABLE ofertaAcademica(\n" +
                        "idoferta INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "idmateria INTEGER,\n" +
                        "iddocente INTEGER,\n" +
                        "idciclo INTEGER,\n" +
                        "descripcion VARCHAR (1024),\n" +
                        "CONSTRAINT fk_idmateria FOREIGN KEY (idmateria) REFERENCES materia(idmateria) ON DELETE RESTRICT,\n" +
                        "CONSTRAINT fk_iddocente FOREIGN KEY (iddocente) REFERENCES docente(iddocente) ON DELETE RESTRICT,\n" +
                        "CONSTRAINT fk_idciclo FOREIGN KEY (idciclo) REFERENCES ciclo(idciclo) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE opcion(\n" +
                        "idopcion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "idpregunta INTEGER,\n" +
                        "descripopc VARCHAR(1024),\n" +
                        "esCorrecta BOOLEAN NOT NULL,\n" +
                        "CONSTRAINT fk_idpregunta FOREIGN KEY (idpregunta) REFERENCES  pregunta(idpregunta) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE opcioncrud(\n" +
                        "idopcion VARCHAR(3)NOT NULL PRIMARY KEY,\n" +
                        "desopcion VARCHAR(30)NOT NULL,\n" +
                        "numcrud INTEGER\n)");


                db.execSQL("CREATE TABLE pregunta(\n" +
                        "idpregunta INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "idarea INTEGER,\n" +
                        "ponderapregunt FLOAT,\n" +
                        "descrippreg VARCHAR(200),\n" +
                        "tipopreg INTEGER,\n" +
                        "CONSTRAINT fk_idarea FOREIGN KEY (idarea) REFERENCES areaEvaluacion(idarea) ON DELETE RESTRICT)");


                db.execSQL("CREATE TABLE respuesta(\n" +
                        "idrespuesta INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "iddetallepreg INTEGER,\n" +
                        "carnet VARCHAR(7),\n" +
                        "CONSTRAINT fk_carnet FOREIGN KEY (carnet) REFERENCES estudiante(carnet) ON DELETE RESTRICT,\n" +
                        "CONSTRAINT fk_iddetallepreg FOREIGN KEY (iddetallepreg) REFERENCES detallePregunta(iddetallepreg) ON DELETE RESTRICT)");


                db.execSQL("INSERT INTO usuario VALUES('admin','administrador',1,0,0)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE1);
                db.execSQL(DROP_TABLE2);
                db.execSQL(DROP_TABLE3);
                db.execSQL(DROP_TABLE4);
                db.execSQL(DROP_TABLE5);
                db.execSQL(DROP_TABLE6);
                db.execSQL(DROP_TABLE7);
                db.execSQL(DROP_TABLE8);
                db.execSQL(DROP_TABLE9);
                db.execSQL(DROP_TABLE10);
                db.execSQL(DROP_TABLE11);
                db.execSQL(DROP_TABLE12);
                db.execSQL(DROP_TABLE13);
                db.execSQL(DROP_TABLE14);
                db.execSQL(DROP_TABLE15);
                onCreate(db);
            } catch (Exception e) {
            }
        }
    }
    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }

    public Cursor ConsultarUsuPasAdmin(String user, String pass) throws SQLException{
        Cursor cursor = null;
        cursor=DBHelper.getReadableDatabase().query("usuario",new String[]{"nomusuario","clave","isadmin","isdocente","isestudiante"},"nomusuario='"+user+"'"+"AND clave='"+pass+"'"+"AND isadmin= 1",null,null,null,null);
        return cursor;
    }

    public Cursor ConsultarUsuPasDocente(String user, String pass) throws SQLException{
        Cursor cursor = null;
        cursor=DBHelper.getReadableDatabase().query("usuario",new String[]{"nomusuario","clave","isadmin","isdocente","isestudiante"},"nomusuario='"+user+"'"+"AND clave='"+pass+"'"+"AND isdocente= 1",null,null,null,null);
        return cursor;
    }

    public Cursor ConsultarUsuPasEstudiante(String user, String pass) throws SQLException{
        Cursor cursor = null;
        cursor=DBHelper.getReadableDatabase().query("usuario",new String[]{"nomusuario","clave","isadmin","isdocente","isestudiante"},"nomusuario='"+user+"'"+"AND clave='"+pass+"'"+"AND isestudiante= 1",null,null,null,null);
        return cursor;
    }



















    public String insertar(Docente docente){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        // verificar que no exista docente
        if(verificarIntegridad(docente,5))
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues docen = new ContentValues();
            docen.put("nombdocente", docente.getNombdocente());
            docen.put("apelldocente", docente.getApelldocente());
            docen.put("nomusuario",docente.getNomusuario() );
            docen.put("correo", docente.getCorreo());
            docen.put("direccion", docente.getDireccion());
            contador=db.insert("docente", null, docen);
            regInsertados=regInsertados+contador;
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }


    public String eliminar(Docente docente){
        System.out.println("ENTRO AL METODO ELIMINAR EN LA CONEXION");
        String regAfectados="filas afectadas= ";
        int contador=0;
        int auxid=0;
        if(verificarIntegridad(docente, 5)) {
            System.out.println("ENTRO A LA VERIFICACION DE INTEGRIDAD");
            Cursor cursorId= db.rawQuery("SELECT iddocente FROM docente WHERE nomusuario='"+docente.getNomusuario()+"'",null);
            if(cursorId.moveToFirst()){
                System.out.println(auxid);
                auxid=cursorId.getInt(0);
                System.out.println(auxid);
            }
            db.delete("ofertaAcademica","iddocente="+auxid,null);
            db.delete("usuario", "nomusuario='"+docente.getNomusuario()+"'",null);
            db.delete("docente", "nomusuario='" +docente.getNomusuario()+"'",null);
            regAfectados+=contador;
        } else {
            return "Registro con Usuario " + docente.getNomusuario() + " no existe";
        }
        return regAfectados;
    }




















    public String insertar(Estudiante estudiante){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        // verificar que no exista docente
        if(verificarIntegridad(estudiante,3))
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues estudiantes = new ContentValues();
            estudiantes.put("carnet", estudiante.getCarnet());
            estudiantes.put("nombreestu", estudiante.getNombreestu());
            estudiantes.put("apellidoestu", estudiante.getApellidoestu());
            estudiantes.put("correoestu", estudiante.getCorreoestu());
            estudiantes.put("direccionestu", estudiante.getDireccionestu());
            estudiantes.put("nomusuario",  estudiante.getNomusuario() );
            contador=db.insert("estudiante", null, estudiantes);
            regInsertados=regInsertados+contador;
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }

    public String eliminar(Estudiante estudiante){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if(verificarIntegridad(estudiante, 3)) {
            db.delete("detalleEstudiante","carnet='"+estudiante.getCarnet()+"'",null);
            db.delete("respuesta", "carnet='" +estudiante.getCarnet()+"'",null);
            db.delete("usuario", "nomusuario='"+estudiante.getCarnet()+"'",null);
            contador+=db.delete("estudiante", "carnet='"+estudiante.getCarnet()+"'", null);
            regAfectados+=contador;
        } else {
            return "Registro con carnet " + estudiante.getCarnet() + " no existe";
        }
        return regAfectados;
    }

















    public String insertar(Usuario us){
        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        // verificar que no exista usuario
        if(verificarIntegridad(us,4))
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else
        {
            ContentValues us1 = new ContentValues();
            us1.put("nomusuario", us.getNomusuario());
            us1.put("clave",us.getClave());
            us1.put("isadmin",us.getAdmin());
            us1.put("isdocente", us.getDocente());
            us1.put("isestudiante", us.getEstudiante());
            contador=db.insert("usuario", null, us1);
            regInsertados=regInsertados+contador;
        }
        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }







    private boolean verificarIntegridad(Object dato, int relacion) throws
            SQLException{
        switch(relacion){
            case 1:
            {
               /* //verificar que al insertar nota exista carnet del alumno y el codigo de materia
                Nota nota = (Nota)dato;
                String[] id1 = {nota.getCarnet()};
                String[] id2 = {nota.getCodmateria()};
                abrir();
                Cursor cursor1 = db.query("alumno", null, "carnet = ?", id1, null,null, null);
                Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2,null, null, null);
                if(cursor2.moveToFirst() && cursor1.moveToFirst()){
                    //Se encontraron datos ||
                    return true;
                }
                return false;
            }*/
               Estudiante estud = (Estudiante) dato;
               String[] id1= {estud.getCarnet()};
               abrir();
               Cursor cursor1 = db.query("estudiante",null,"carnet = ?",id1,null,null,null,null);
               if (cursor1.moveToFirst()){
                   return true;
               }
               return false;

            }
            case 2: {
                /*//verificar que al modificar nota exista carnet del alumno, el codigo de materia y el ciclo
                //Nota nota1 = (Nota)dato;
                String[] ids = {nota1.getCarnet(), nota1.getCodmateria(), nota1.getCiclo()};
                abrir();
                Cursor c = db.query("nota", null, "carnet = ? AND codmateria = ? AND ciclo = ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }*/
                return true;
            }
            case 3: {
                Estudiante estudiante = (Estudiante) dato;
                String[] id = {estudiante.getCarnet()};
                abrir();
                Cursor c2 = db.query("estudiante", null, "carnet = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 4: {
                Usuario user = (Usuario) dato;
                String[] id = {user.getNomusuario()};
                abrir();
                Cursor c2 = db.query("usuario", null, "nomusuario = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 5:
            {
                //verificar que exista alumno
                Docente docente = (Docente) dato;
                String[] id = {docente.getNomusuario()};
                abrir();
                Cursor c2 = db.query("docente", null, "nomusuario = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 6:
            {
                /*//verificar que exista Materia
                Materia materia2 = (Materia)dato;
                String[] idm = {materia2.getCodmateria()};
                abrir();
                Cursor cm = db.query("materia", null, "codmateria = ?", idm, null, null, null);
                if(cm.moveToFirst()){
                    //Se encontro Materia
                    return true;
                }*/
                return false;
            }
            default:
                return false;
        }
    }
}
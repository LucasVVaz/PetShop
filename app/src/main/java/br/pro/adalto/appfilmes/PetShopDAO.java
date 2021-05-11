package br.pro.adalto.appfilmes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PetShopDAO {

    public static void inserir(PetShop petShop, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nomeCao", petShop.nomeCao);
        valores.put("nomeDono", petShop.nomeDono );
        valores.put("endereco", petShop.endereco );
        valores.put("idadeCao", petShop.getIdadeCao() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("petShop", null, valores);
    }

    public static void editar(PetShop petShop, Context context){
        ContentValues valores = new ContentValues();
        valores.put("nomeCao", petShop.nomeCao);
        valores.put("nomeDono", petShop.nomeDono );
        valores.put("endereco", petShop.endereco );
        valores.put("idadeCao", petShop.getIdadeCao() );

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("petShop", valores, " id = " + petShop.id , null );
    }

    public static void excluir(int id, Context context){
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("petShop", " id = " + id , null);
    }

    public static List<PetShop> getPet(Context context){
        List<PetShop> lista = new ArrayList<>();
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nomeCao, nomeDono, endereco, idadeCao FROM petShop ORDER BY nomeCao", null );
        if( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                PetShop petShop = new PetShop();
                petShop.id = cursor.getInt( 0);
                petShop.nomeCao = cursor.getString(1);
                petShop.nomeDono = cursor.getString(2);
                petShop.endereco = cursor.getString(3);
                petShop.setIdadeCao( cursor.getInt(4) );
                lista.add(petShop);
            }while( cursor.moveToNext() );
        }
        return lista;
    }

    public static PetShop getPetById(Context context, int id) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, nomeCao, nomeDono, endereco,idadeCao FROM petShop WHERE id = " + id, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            PetShop petShop = new PetShop();
            petShop.id = cursor.getInt(0);
            petShop.nomeCao = cursor.getString(1);
            petShop.nomeDono = cursor.getString(2);
            petShop.endereco = cursor.getString(3);
            petShop.setIdadeCao(cursor.getInt(4));
            return petShop;
        } else {
            return null;
        }
    }
}

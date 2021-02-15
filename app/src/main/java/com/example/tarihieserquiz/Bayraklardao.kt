package com.example.tarihieserquiz

class Bayraklardao {

    fun rastgele5bayrakgetir(vt:VeritabaniYardimcisi) : ArrayList<Bayraklar>{
        val bayraklarliste = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("Select * from bayraklar order by random() limit 10",null)
        while (c.moveToNext()){
            val bayrak = Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
                ,c.getString(c.getColumnIndex("bayrak_ad"))
                ,c.getString(c.getColumnIndex("bayrak_resim")))
            bayraklarliste.add(bayrak)
        }
        return bayraklarliste
    }
    fun rastgele3yanlissecenekgetir(vt:VeritabaniYardimcisi,bayrak_id:Int) : ArrayList<Bayraklar>{
        val bayraklarliste = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("Select * from bayraklar where bayrak_id!=$bayrak_id order by random() limit 3",null)
        while (c.moveToNext()){
            val bayrak = Bayraklar(c.getInt(c.getColumnIndex("bayrak_id"))
                ,c.getString(c.getColumnIndex("bayrak_ad"))
                ,c.getString(c.getColumnIndex("bayrak_resim")))
            bayraklarliste.add(bayrak)
        }
        return bayraklarliste
    }
}
package br.pro.adalto.appfilmes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterPetShop extends BaseAdapter {

    private List<PetShop> petShopList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterPetShop(Context context, List<PetShop> listaPetShops){
        this.petShopList = listaPetShops;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return petShopList.size();
    }

    @Override
    public Object getItem(int i) {
        return petShopList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return petShopList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvNomeCao = convertView.findViewById(R.id.tvListaNomeCao);
            item.tvEndereco = convertView.findViewById(R.id.tvListaEndereco);
            item.tvNomeDono = convertView.findViewById(R.id.tvListaNomeDono);
            item.tvIdadeCao = convertView.findViewById(R.id.tvListaIdadeCao);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        PetShop petShop = petShopList.get(i);
        item.tvNomeCao.setText(  petShop.nomeCao);
        item.tvNomeDono.setText(  petShop.nomeDono);
        item.tvEndereco.setText(  petShop.endereco );
        item.tvIdadeCao.setText(  String.valueOf( petShop.getIdadeCao() ) );

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNomeCao, tvNomeDono, tvEndereco, tvIdadeCao;
        LinearLayout layout;
    }
}

package com.example.pi.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pi.R;
import com.example.pi.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoListAdapter extends RecyclerView.Adapter<ProdutoListAdapter.ListViewHolder> implements Filterable {

    private List<Produto> produtos;
    private List<Produto> listaCheiaProdutos;
    private Context context;
    //RequestOptions option;

    public ProdutoListAdapter(List<Produto> produtos, Context context) {
        this.produtos = produtos;
        this.context = context;
        listaCheiaProdutos = new ArrayList<>(produtos);
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        LayoutInflater inflater =  LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_list_produto,viewGroup,false);
        ListViewHolder listViewHolder = new ListViewHolder(view);


        return listViewHolder;
    }





    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int position) {

       //Captura os clicks nos cardViews
        listViewHolder.itemView.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Produto produto = produtos.get(position);

                       System.out.println(position);


                      /* if ((v.findViewById(R.id.detalhe) != null) || (v.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)){

                           ProdutoDetalheFragment fragment = ProdutoDetalheFragment.instanciaFragment(produto);
                           FragmentManager fm =   ((AppCompatActivity) context).getSupportFragmentManager();

                           FragmentTransaction ft = fm.beginTransaction();
                           ft.replace(R.id.detalhe, fragment, ProdutoDetalheFragment.TAG_DETALHE);
                           ft.commit();

                       }else {


                           Intent intent = new Intent(v.getContext(), ProdutoDetalheActivity.class);
                           intent.putExtra("produto", produto);
                           v.getContext().startActivity(intent);

                       }*/

                   }
               }
       );

        Produto produto = produtos.get(position);

        TextView nome_produto =  listViewHolder.nomeProduto;
        nome_produto.setText(String.valueOf(produto.getNome()));

        TextView preco_produto =  listViewHolder.precoProduto;
        preco_produto.setText(String.valueOf(produto.getPreco()));

        TextView qnt_produto =  listViewHolder.qntProduto;
        qnt_produto.setText(String.valueOf(produto.getQnt()));


        //ImageView imagem = listViewHolder.imgProduto;
        //Resources res = context.getResources();
        //imagem.setImageResource(produtos.get(position).getImg());
        //imagem.setImageResource(produtos.get(position).getImg());

        //Glide.with(context).load(produtos.get(position).getImg()).apply(option).into(listViewHolder.imgProduto);

        //Picasso.with(context).load(produtos.get(position).getImg()).into(listViewHolder.imgProduto);
    }





    public class ListViewHolder extends  RecyclerView.ViewHolder {

        public ImageView imgProduto;
        public TextView nomeProduto;
        public TextView precoProduto;
        public TextView qntProduto;


        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduto = (ImageView) itemView.findViewById(R.id.img_produto);
            nomeProduto = (TextView) itemView.findViewById(R.id.nome_produto);
            precoProduto = (TextView) itemView.findViewById(R.id.preco_produto);
            qntProduto = (TextView) itemView.findViewById(R.id.qntestoque);
        }
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    @Override
    public Filter getFilter() {

        return  produtoFiltro;
    }

    Filter produtoFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Produto> listaFiltrada = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                listaFiltrada.addAll(listaCheiaProdutos);
            }else {
                String filtroPadrao = constraint.toString().toLowerCase().trim();

                for (Produto produto : listaCheiaProdutos){
                    if (produto.getNome().toLowerCase().contains(filtroPadrao)){
                        listaFiltrada.add(produto);
                    }
                }

            }

            FilterResults resultados = new FilterResults();

            resultados.values = listaFiltrada;

            return resultados;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            produtos.clear();
            produtos.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

}

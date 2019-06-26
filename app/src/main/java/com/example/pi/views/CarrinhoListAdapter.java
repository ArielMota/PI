package com.example.pi.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pi.ImagemPagerAdapter;
import com.example.pi.R;
import com.example.pi.manipulacao_api.APIconfig;
import com.example.pi.manipulacao_api.Busca_carrinho;
import com.example.pi.manipulacao_api.Busca_imagens;
import com.example.pi.manipulacao_api.Excluir_item_carrinho;
import com.example.pi.manipulacao_api.FinalizarCompras;
import com.example.pi.model.ItemCarrinho;
import com.example.pi.model.Produto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoListAdapter extends RecyclerView.Adapter<CarrinhoListAdapter.ListViewHolder> implements Filterable {

    private List<Produto> produtos;
    private List<Produto> listaCheiaProdutos;
    List<ItemCarrinho> itemCarrinhos;
    private Context context;
    Dialog mDialog;
    ViewPager mViewPager;
    Activity activity;
    Double valorTotal = 0.0;
    View viewcar;
    String token;

    //RequestOptions option;

    public CarrinhoListAdapter(List<ItemCarrinho> itemCarrinhos,List<Produto> produtos, Context context, Activity activity,View viewcar) {
        this.produtos = produtos;
        this.itemCarrinhos = itemCarrinhos;
        this.context = context;
        this.activity = activity;
        this.viewcar = viewcar;
        listaCheiaProdutos = new ArrayList<>(produtos);
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        token = activity.getIntent().getExtras().getString("token");


        context = viewGroup.getContext();
        LayoutInflater inflater =  LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_list_carrinho,viewGroup,false);
        final ListViewHolder listViewHolder = new ListViewHolder(view);

        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.dialog_finalizarcompra);

        FloatingActionButton fab = viewcar.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.show();

                TextView textView =  mDialog.findViewById(R.id.precototal);

                textView.setText(String.valueOf(valorTotal));

                Button nao = (Button) mDialog.findViewById(R.id.button_nao);
                nao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mDialog.dismiss();
                    }
                });

                Button sim = (Button) mDialog.findViewById(R.id.button_sim);
                sim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        FinalizarCompras finalizarCompras = new FinalizarCompras();

                        finalizarCompras.finalizacompra(context,activity,view,token);

                        mDialog.dismiss();


                    }
                });


            }
        });








        return listViewHolder;
    }





    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int position) {



        final Produto produto = produtos.get(position);
        final ItemCarrinho itemCarrinho = itemCarrinhos.get(position);


        //valorTotal += produto.getPreco() * itemCarrinho.getQuantidade();
        //System.out.println(valorTotal);

        System.out.println(produto.getPreco());
        System.out.println(itemCarrinho.getQuantidade());

        valorTotal += (Double.valueOf(produto.getPreco() * itemCarrinho.getQuantidade()));
        System.out.println(valorTotal);

        TextView nome_produto =  listViewHolder.nomeProduto;
        nome_produto.setText(String.valueOf(produto.getNome()));

        TextView preco_produto =  listViewHolder.precoProduto;
        preco_produto.setText(String.valueOf(produto.getPreco()));

        TextView qnt_produto =  listViewHolder.qntProduto;
        qnt_produto.setText(String.valueOf(itemCarrinho.getQuantidade()));

        ImageView imageView = (ImageView) listViewHolder.imgProduto;
        Picasso.get().load(APIconfig.URL+"/imagem/"+produtos.get(position).getLista_id_imagens().get(0)).resize(500,500)
                .centerCrop().into(imageView);


        listViewHolder.img_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCarrinhos.remove(position);
                produtos.remove(position);
                notifyItemRemoved(position);

                Excluir_item_carrinho excluir_item_carrinho = new Excluir_item_carrinho();
                excluir_item_carrinho.excluirItemCarrinho(context,produto.getId(),token);

            }
        });


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
        public ImageView img_x;

        private CardView item_produto;



        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProduto = (ImageView) itemView.findViewById(R.id.img_produto_car);
            nomeProduto = (TextView) itemView.findViewById(R.id.nome_produto_car);
            precoProduto = (TextView) itemView.findViewById(R.id.preco_produto_car);
            qntProduto = (TextView) itemView.findViewById(R.id.qnt_car);
            item_produto = (CardView) itemView.findViewById(R.id.produto_item_card);
            img_x = (ImageView) itemView.findViewById(R.id.img_x);

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

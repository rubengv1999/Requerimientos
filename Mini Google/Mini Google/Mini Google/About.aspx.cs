using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Mini_Google
{
    public partial class About : Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }


        protected void Button1_Click(object sender, EventArgs e)
        {
            string Query = Text.Value;
            RadioButtonResultados.Items.Clear();
            List<List<string>> documentos = documents(Query);
            int contador = 1;
            if (Query.Contains("music"))
            {
                RadioButtonResultados.Items.Add(new ListItem("[" + contador + "] " + "Music Picture", "Music Picture"));
                contador++;
            }
            if (Query.Contains("dvd"))
            {
                RadioButtonResultados.Items.Add(new ListItem("[" + contador + "] " + "DVD Picture", "DVD Picture"));
                contador++;
            }

            foreach (List<string> document in documentos)
            {

                RadioButtonResultados.Items.Add(new ListItem("[" + contador + "] " + document[0], document[1]));
                contador++;
            }
        }
        public List<List<String>> documents(string query)
        {
            SqlConnection con = new SqlConnection("Data Source=DESKTOP-DEO1EC6;Initial Catalog=Mini Google;Integrated Security=True");
            SqlCommand com = new SqlCommand("MakeQuery", con);
            com.CommandType = CommandType.StoredProcedure;
            com.Parameters.Add("@query", SqlDbType.VarChar).Value = query;
            SqlDataAdapter adapt = new SqlDataAdapter(com);
            DataTable dataset = new DataTable();
            adapt.Fill(dataset);
            List<List<String>> documents = new List<List<String>>();
            foreach (DataRow row in dataset.Rows)
            {
                string Titulo = row["Titulo"].ToString();
                string Texto = row["Texto"].ToString();
                List<String> document = new List<string>();
                document.Add(Titulo);
                document.Add(Texto);
                documents.Add(document);
            }
            return documents;
        }

        protected void RadioButtonResultados_SelectedIndexChanged(object sender, EventArgs e)
        {
            Panel2.Visible = true;

            string selected = RadioButtonResultados.SelectedValue;

            if (selected == "Music Picture")
            {
                imagen.Src = "music.png";
                Panel1.Visible = true;
            }
            else if (selected == "DVD Picture")
            {
                imagen.Src = "dvd.png";
                Panel1.Visible = true;
            }
            else
            {
                Panel1.Visible = false;
            }
            Label2.Text = RadioButtonResultados.SelectedItem.Text;
            Label1.Text = selected;
        }
    }
}
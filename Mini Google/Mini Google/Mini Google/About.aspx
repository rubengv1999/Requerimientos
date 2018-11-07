<%@ Page Title="About" Language="C#" MasterPageFile="~/Site.Master" AutoEventWireup="true" CodeBehind="About.aspx.cs" Inherits="Mini_Google.About" %>

<asp:Content ID="BodyContent" ContentPlaceHolderID="MainContent" runat="server">

    <img src="google.png" class="img-rounded" alt="Cinque Terre">
    <div class="container">
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <table style="width: 100%;">
                        <tr>
                            <td>
                                <label for="usr">Texto:</label>
                            </td>
                            <td>
                                <input type="text" runat="server" class="form-control input-sm" id="Text" autocomplete="off">
                            </td>
                            <td>
                                <asp:Button ID="Button1" class="btn" runat="server" OnClick="Button1_Click" Text="Buscar" Style="color: #FFFFFF; background-color: #0066FF" />

                            </td>

                        </tr>
                    </table>


                </div>
            </div>
            <div class="col-sm-12">
            </div>

        </div>
    </div>

    <table style="width: 100%;">
        <tr>
            <td>
                <asp:RadioButtonList ID="RadioButtonResultados" runat="server" Width="500px" AutoPostBack="True" OnSelectedIndexChanged="RadioButtonResultados_SelectedIndexChanged">
                </asp:RadioButtonList>

            </td>
            <td>
                <asp:Panel ID="Panel2" Visible="false" runat="server">
                    <h1>Resultado de la búsqueda</h1>
                    <strong>
                        <asp:Label ID="Label2" runat="server" Style="font-size: x-large"></asp:Label>
                        <br />
                    </strong>
                    <br />
                    <asp:Label ID="Label1" runat="server" Text=""></asp:Label>
                    <asp:Panel ID="Panel1" Visible="false" runat="server">
                        <img src="music.png" id="imagen" runat="server" class="img-rounded" alt="Cinque Terre">
                    </asp:Panel>
                </asp:Panel>
            </td>
        </tr>
    </table>

    </div>
    </div>
</asp:Content>

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace U5Runes
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs e)
        {
            if (RuneBox == null) return;

            Style lineStyle = (Style)FindResource("CenteredText");

            // STEP 1: Clear the existing glyphs
            RuneBox.Children.Clear();

            // STEP 2: Go line by line... looking up runes
            foreach(var line in English.Text.Split(new[] {'\r','\n'}))
            {
                var transformed = line.ToLower().Trim();
                var len = transformed.Length;

                var linePanel = new StackPanel { Style=lineStyle };
                var idx = 1;
                while (idx <= len) 
                {
                    var rune = $"Rune-{transformed.Substring(idx-1,(idx < len) ? 2 : 1)}";
                    Style letter = (Style)TryFindResource(rune);
                    if(letter == null)
                    {
                        letter = (Style)TryFindResource(rune.Substring(0,6));
                        idx += 1;
                    } else
                    {
                        idx += 2;
                    }
                    if(letter != null)
                    {
                      linePanel.Children.Add(new Control { Style = letter });
                    }
                }

                if(linePanel.Children.Count > 0)
                    RuneBox.Children.Add(linePanel);
            }
        }
    }
}

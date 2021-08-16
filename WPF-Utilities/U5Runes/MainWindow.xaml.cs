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
using System.Windows.Threading;

namespace U5Runes
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private DispatcherTimer RedrawTimer = null;

        public MainWindow()
        {
            InitializeComponent();
            SolidColorBrush rc = (SolidColorBrush)Resources["RuneColor"];
            Binding b = new Binding
            {
                Source = FGColor,
                Path = new PropertyPath("Text"),
                Mode = BindingMode.OneWay
            };
            BindingOperations.SetBinding(rc, SolidColorBrush.ColorProperty, b);
        }

        private void RedrawRunes()
        {
            if (RuneBox == null) return;

            Style lineStyle = (Style)FindResource("CenteredText");

            // STEP 1: Clear the existing glyphs
            RuneBox.Children.Clear();

            // STEP 2: Go line by line... looking up runes
            foreach (var line in English.Text.Split(new[] { '\r', '\n' }))
            {
                var transformed = line.ToLower().Trim();
                var len = transformed.Length;

                var linePanel = new StackPanel { Style = lineStyle };
                var idx = 1;
                while (idx <= len)
                {
                    var rune = $"Rune-{transformed.Substring(idx - 1, (idx < len) ? 2 : 1)}";
                    Style letter = (Style)TryFindResource(rune);
                    if (letter == null)
                    {
                        letter = (Style)TryFindResource(rune.Substring(0, 6));
                        idx += 1;
                    }
                    else
                    {
                        idx += 2;
                    }
                    if (letter != null)
                    {
                        linePanel.Children.Add(new Control { Style = letter });
                    }
                }

                if (linePanel.Children.Count > 0)
                    RuneBox.Children.Add(linePanel);
            }
        }

        private void TextBox_TextChanged(object sender, TextChangedEventArgs eargs)
        {
            if (RedrawTimer == null)
            {
                RedrawTimer = new DispatcherTimer
                {
                    Interval = new TimeSpan(0, 0, 1)
                };
                RedrawTimer.Tick += (s, e) =>
                {
                    RedrawRunes();
                    RedrawTimer.Stop();
                };
            }
            RedrawTimer.Stop();
            RedrawTimer.Start();
        }

        /// <summary>
        /// Updates the resource dictionary's spacing entry when the contents
        /// of the UI textbox change.  I couldn't do this with simple binding
        /// because Thickness doesn't support bindings.
        /// </summary>
        /// <param name="sender">Event arg</param>
        /// <param name="e">event arg--used for the Source property</param>
        private void CharSpace_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextBox src = (TextBox)e.Source;
            if (Double.TryParse(src.Text, out double width))
                Resources["CharSpacing"] = new Thickness(width, 0, width, 0);
        }

        /// <summary>
        /// Updates the resource dictionary's spacing entry when the contents
        /// of the UI textbox change.  I couldn't do this with simple binding
        /// because Thickness doesn't support bindings.
        /// </summary>
        /// <param name="sender">Event arg</param>
        /// <param name="e">event arg--used for the Source property</param>
        private void LineSpace_TextChanged(object sender, TextChangedEventArgs e)
        {
            TextBox src = (TextBox)e.Source;
            if (Double.TryParse(src.Text, out double width))
                Resources["LineMargin"] = new Thickness(0, width, 0, width);
        }


        // Helper code from: https://blogs.msdn.microsoft.com/jaimer/2009/07/03/rendertargetbitmap-tips/
        private static BitmapSource CaptureScreen(Visual target, double dpiX, double dpiY)
        {
            if (target == null)
            {
                return null;
            }
            Rect bounds = VisualTreeHelper.GetDescendantBounds(target);
            RenderTargetBitmap rtb = new RenderTargetBitmap((int)(bounds.Width * dpiX / 96.0),
                                                            (int)(bounds.Height * dpiY / 96.0),
                                                            dpiX,
                                                            dpiY,
                                                            PixelFormats.Pbgra32);
            DrawingVisual dv = new DrawingVisual();
            using (DrawingContext ctx = dv.RenderOpen())
            {
                VisualBrush vb = new VisualBrush(target);
                ctx.DrawRectangle(vb, null, new Rect(new Point(), bounds.Size));
            }
            rtb.Render(dv);
            return rtb;
        }

        private void SaveClipb_Click(object sender, RoutedEventArgs e)
        {
            var dpi = VisualTreeHelper.GetDpi(RunePic);
            var bms = CaptureScreen(RunePic,dpi.PixelsPerInchX,dpi.PixelsPerInchY);
            Clipboard.SetImage(bms);
        }
    }
}

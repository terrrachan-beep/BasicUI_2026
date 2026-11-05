package tw.edu.pu.csim.tcyang.basicui_2026.s1141706

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import tw.edu.pu.csim.tcyang.basicui_2026.s1141706.ui.theme.BasicUI_2026Theme
import androidx.compose.material3.Button


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicUI_2026Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Main(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun Main (modifier: Modifier = Modifier) {
    var currentVideo by remember {
        mutableStateOf(R.raw.love)
    }
    Box (modifier = modifier.fillMaxSize()) {
        LazyColumnExample()
        ColumnExample()
        TextFieldExample(modifier)
        Column{
            SimpleVideoPlayer(currentVideo)
            Button(
                onClick = {
                    currentVideo =
                        if (currentVideo == R.raw.love) {
                            R.raw.handpan
                        } else {
                            R.raw.love
                        }
                }
            ) {
                Text(
                    text = if (currentVideo == R.raw.love) {
                        "切換至手碟影片"
                    } else {
                        "切換至Love影片"
                    }
                )
            }
            LazyRowExample(modifier)
        }

    }

}

@Composable
fun LazyColumnExample(modifier: Modifier = Modifier) {
    LazyColumn (modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        // 標題列
        item {
            Text(text = "標題列")
        }

        // Add 50 items
        items(50) { index ->
            Text(text = "$index")
        }

        // Add another single item
        item {
            Text(text = "結尾列")
        }
    }
}

@Composable
fun ColumnExample(modifier: Modifier = Modifier) {
    var msg by remember { mutableStateOf("") }
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0BBE4))
            .pointerInput(Unit) {
                detectTapGestures (
                    onTap = { msg = "短按"},
                    onDoubleTap = { msg = "連按兩下"},
                    onLongPress = { msg = "長按"}
                )
            },
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("行動應用軟體開發：基本元件")
        Text("作者：寺崎七緒")
        Text("操作結果：$msg")

        Spacer(modifier = Modifier.height(8.dp))
        RowExample(modifier)

    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    BasicUI_2026Theme {
        Main()
    }
}

@Composable
fun RowExample(modifier: Modifier = Modifier) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.android),
            contentDescription = "Android icon",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Yellow),
            alpha = 0.6f,
        )

        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(id = R.drawable.compose),
            contentDescription = "Compose icon",
            modifier = Modifier.size(100.dp)
        )
    }
}

@Composable
fun TextFieldExample(modifier: Modifier = Modifier) {
    // 管理輸入框的文字狀態
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("請輸入文字") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (inputText.isBlank()) "尚未輸入內容" else "您輸入的是：$inputText"
        )
    }
}

data class Animal(
    val name: String,
    @DrawableRes val imageRes: Int
)

@Composable
fun LazyRowExample(modifier: Modifier = Modifier) {
    val animals = listOf(
        Animal("鴨子", R.drawable.animal0),
        Animal("企鵝", R.drawable.animal1),
        Animal("青蛙", R.drawable.animal2),
        Animal("貓頭鷹", R.drawable.animal3),
        Animal("海豚", R.drawable.animal4),
        Animal("牛", R.drawable.animal5),
        Animal("無尾熊", R.drawable.animal6),
        Animal("獅子", R.drawable.animal7),
        Animal("狐狸", R.drawable.animal8),
        Animal("小雞", R.drawable.animal9)
    )
    val context = LocalContext.current

    LazyRow(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // 直接傳入 animals 清單，每個迴圈拿到一個 animal 物件
        itemsIndexed(animals) { index,animal ->

            Card(
                modifier = Modifier.fillParentMaxWidth(1.0f)
                    .height(260.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD0BCFF)
                )
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = animal.imageRes),
                        contentDescription = animal.name,
                        modifier = Modifier.size(150.dp)
                            .clickable {
                                // 點擊時跳出 Toast 訊息
                                Toast.makeText(
                                    context,
                                    "這是 ${animal.name}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "$index ${animal.name}"
                    )
                }

            }


        }
    }
}

@Composable
fun SimpleVideoPlayer(
    @RawRes videoRes: Int
) {
    val context = LocalContext.current

    // 建立並記住 ExoPlayer 實體，設定影片路徑與自動播放
    val exoPlayer = remember(videoRes) {
        ExoPlayer.Builder(context).build().apply {
            val uri = "android.resource://${context.packageName}/$videoRes"
            setMediaItem(MediaItem.fromUri(uri))
            prepare()
            playWhenReady = true // 載入完成自動播放
        }
    }
    // 利用 DisposableEffect 管理生命週期
    // 離開畫面時自動釋放記憶體
    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release() // 💡 釋放播放器資源
        }
    }
    //將原生的 PlayerView 嵌入 Compose
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        update = { playerView ->
            playerView.player = exoPlayer
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}



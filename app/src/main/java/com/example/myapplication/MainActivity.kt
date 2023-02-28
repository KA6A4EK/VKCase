package com.example.myapplication

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivity
import androidx.core.content.ContextCompat
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   VkCall()
                } } } } }

@Composable
fun VkCall() {
    VkCallScreen()
}

@Composable
fun VkCallScreen(modifier: Modifier = Modifier
    .background(Color(18, 18, 18))
    .fillMaxSize()) {
    var result by remember { mutableStateOf(false) }
    var micOn by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button1Minimize()
        }
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Button2Chat()
            Button3Friends()
            result = button4SwapTiles(result)
        }
        CallImages(result = result,micOn=micOn)
            Button9()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom,
                modifier = modifier
                    .padding(bottom = 16.dp, end = 16.dp, start = 16.dp)
                    .fillMaxWidth()
            ) {
                Button5Camera()
                micOn=Button6Microphone()
                Button7AlertDialog()
                Button8DropTheCall() }}}

@Composable
fun Button9(){
    Image(painter = painterResource(id = R.drawable.minimize_48px),
        contentDescription ="",
        modifier = Modifier
            .clickable {}
            .width(90.dp)
            .fillMaxWidth()
            .padding(top = 16.dp)) }

@Composable
fun CallImages(result:Boolean,micOn:Boolean){
    val configuration = LocalConfiguration.current
    val columnHeight =(configuration.screenHeightDp-267.0)
    Column(modifier = Modifier
        .height(columnHeight.dp)
        .padding(start = 4.dp, end = 4.dp)) {
        CallImage(height_images = ((columnHeight-4)/2), result =result,micOn= micOn )
        Spacer(modifier = Modifier
            .height(4.dp)
            .fillMaxWidth())
        CallImage(height_images = ((columnHeight-4)/2), result =!result,micOn= micOn ) } }

@Composable
fun CallImage(
   height_images : Double,
    result:Boolean,
   micOn : Boolean
) {
    val configuration = LocalConfiguration.current
    val images = when (result){
    true ->R.drawable.hyrk8tahx9f7xzfk2pkm40wg852ucwpgz5lweqplzefninrbp6h1akwzzn_ad_tyngofgt9kvx2ekkuiztdr8m2s
    false->R.drawable._0230204_122152 }
    val names = when (result){
        true ->stringResource(R.string.user_name_1)
        false->stringResource(R.string.user_name_2 )}
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height_images.dp)) {
    Image(painter = painterResource(id = images),
        contentDescription =null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .blur(
                radiusX = 50.dp,
                radiusY = 50.dp,
                edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(16.dp))
            ))
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(height_images.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween)
    {
        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())
        Image(
            painter = painterResource(id = images),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape))
        Row(
        modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.Center){
        Text(text = names,
            modifier = Modifier
                .padding(bottom = 5.dp)
                .widthIn(max = (configuration.screenWidthDp-50).dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 18.sp,
            color =Color.White)
            if (!micOn){
        Image(
            painter = painterResource(id = R.drawable.mic_offcall_48px),
            contentDescription =null,
        ) } }} }}

@Composable
fun Button1Minimize(){
    Image(
        painter = painterResource(id = R.drawable.arrow_drop_down_48px),
        contentDescription = stringResource(R.string.buttonMinimize),
        modifier = Modifier
            .clickable{}) }

@Composable
fun Button2Chat(){
    val activity = LocalContext.current as Activity
    var k by remember { mutableStateOf(false) }
    if (k) {
        openSMS(context = LocalContext.current, activity = activity)
        k=!k }
    Image(
        painter = painterResource(id = R.drawable.chat_bubble_48px),
        contentDescription = stringResource(R.string.chat_button),
        modifier = Modifier
            .clickable { k = !k }
            .size(37.dp))}

@Composable
fun Button3Friends() {
    val activity = LocalContext.current as Activity
    var k by remember { mutableStateOf(false) }
    if (k) {
        openContacts(context = LocalContext.current, activity = activity)
        k=!k }
    Image(
        painter = painterResource(id = R.drawable.person_add_48px),
        contentDescription = stringResource(R.string.contacts),
        modifier = Modifier
            .clickable { k = !k }
            .size(37.dp))}

fun openSMS(
    context: Context ,
    activity: Activity){
    if (hasMessagePermission(context)) {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING)
        startActivity(context, intent,null)}
    else {
        requestMessagePermission( activity) }}

fun openContacts(
    context: Context ,
    activity: Activity) {
    if (hasContactPermission(context)) {
        val intent = Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI)
        startActivity(context, intent,null) }
    else {
        requestContactPermission( activity) }}

@Composable
fun button4SwapTiles(result_in: Boolean):Boolean{
    var result by remember { mutableStateOf(result_in) }

    Image(
        painter = painterResource(id = R.drawable.backup_table_48px),
        contentDescription = stringResource(R.string.change_of_people_button),
        modifier = Modifier
            .clickable { result = !result }
            .size(37.dp))
    return result}

@Composable
fun Button5Camera(){
    var cameraOn by remember { mutableStateOf(true) }
    val images = when(cameraOn) {
        true -> R.drawable.videocam_48px
        false -> R.drawable.videocam_off_48px }
    val cameraButtonColor = when (cameraOn){
        true -> 0xFF323232
        false -> 0xFFFFFFFF
    }

    Image(painter = painterResource(id = images),
        contentDescription = stringResource(R.string.camera_button),
        modifier = Modifier
            .clickable { cameraOn = !cameraOn }
            .border(BorderStroke(11.dp, Color(cameraButtonColor)), CircleShape)
            .padding(11.dp)
            .clip(CircleShape)
            .background(Color(cameraButtonColor))
            .size(37.dp)) }

@Composable
fun Button6Microphone():Boolean{
    var microphoneOn by remember { mutableStateOf(true) }
    val images = when(microphoneOn){
        true -> R.drawable.mic_48px
        false -> R.drawable.mic_off_48px }
    val microphoneButtonColor = when (microphoneOn){
        true -> 0xFF323232
        false -> 0xFFFFFFFF
    }
    Image(painter = painterResource(id = images),
        contentDescription = stringResource(R.string.microphone_button),
        modifier = Modifier
            .clickable { microphoneOn = !microphoneOn }
            .border(BorderStroke(11.dp, Color(microphoneButtonColor)), CircleShape)
            .padding(11.dp)
            .clip(CircleShape)
            .background(Color(microphoneButtonColor))
            .size(37.dp))
        return microphoneOn}

@Composable
fun Button7AlertDialog() {
    var openDialog by remember { mutableStateOf(false) }
    if (openDialog) {
        AlertDialog(
            onDismissRequest = {
                openDialog = false },

            title = { Text(text = stringResource(R.string.alertdialog_text), fontSize = 32.sp) },
            buttons = {
                Button(
                    onClick = { openDialog= false }) {
                    Text(stringResource(R.string.alertdialog_button_text), fontSize = 30.sp)
                }})}
    Image(painter = painterResource(id = R.drawable.waving_hand_48px),
        contentDescription = stringResource(R.string.alertdialog),
        modifier = Modifier
            .clickable { openDialog = !openDialog }
            .border(BorderStroke(11.dp, Color(0xFF323232)), CircleShape)
            .padding(11.dp)
            .clip(CircleShape)
            .background(Color(0xFF323232))
            .size(37.dp)) }

@Composable
fun Button8DropTheCall(){
    Image(
        painter = painterResource(id = R.drawable.call_end_48px),
        contentDescription = stringResource(R.string.drop_call_button),
        modifier = Modifier
            .clickable { android.os.Process.killProcess(android.os.Process.myPid()) }
            .border(BorderStroke(11.dp, Color.Red), CircleShape)
            .padding(11.dp)
            .clip(CircleShape)
            .background(Color.Red)
            .size(37.dp)) }

fun hasContactPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) ==
            PackageManager.PERMISSION_GRANTED; }

fun requestContactPermission( activity: Activity) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_CONTACTS), 1)}

fun hasMessagePermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_SMS) ==
            PackageManager.PERMISSION_GRANTED; }

fun requestMessagePermission( activity: Activity) {
    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_SMS), 1)
    }
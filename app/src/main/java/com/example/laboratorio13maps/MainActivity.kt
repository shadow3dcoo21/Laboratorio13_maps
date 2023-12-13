package com.example.laboratorio13maps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio13maps.ui.theme.Laboratorio13mapsTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MyMap()
        }
    }
}


@Composable
fun MyMap(){
    val location = LatLng(-16.429135, -71.519663)

    val camLocation = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 17f)
    }
    var mapUiSettings by remember {
        mutableStateOf(MapUiSettings())
    }

    var mapProperties by remember {
        mutableStateOf(MapProperties(mapType = MapType.SATELLITE))
    }
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(modifier = Modifier.matchParentSize(),
            properties = mapProperties,
            cameraPositionState = camLocation
        ) {
            Marker(
                state= MarkerState(position = location),
                title="Mi Casa XD",
                snippet="Aqui Vivo yo"
            )
        }
        Switch(
            checked = mapProperties.mapType == MapType.NORMAL,
            onCheckedChange = { isChecked ->
                mapProperties = mapProperties.copy(
                    mapType = if (isChecked) MapType.NORMAL else MapType.SATELLITE
                )
            },
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        )
        Button(onClick = {
            // Move the camera to a new zoom level
            camLocation.move(CameraUpdateFactory.zoomIn())
        }) {
            Text(text = "Zoom In")
        }


    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Laboratorio13mapsTheme {
        MyMap()
    }
}
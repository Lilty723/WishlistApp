import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wishlistapp.WishlistAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_item.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var wishlistAdapter: WishlistAdapter
    private val wishlist = mutableListOf<WishlistItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        wishlistAdapter = WishlistAdapter(wishlist)
        recyclerView.adapter = wishlistAdapter

        val fabAdd = null
        fabAdd.setOnClickListener {
            showAddItemDialog()
        }
    }

    private fun showAddItemDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_item, null)
        MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .setTitle("Add Item to Wishlist")
            .setPositiveButton("Add") { dialog, _ ->
                val name = dialogView.editTextName.text.toString()
                val price = dialogView.editTextPrice.text.toString()
                val url = dialogView.editTextUrl.text.toString()
                val item = WishlistItem(name, price, url)
                wishlist.add(item)
                wishlistAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}


}

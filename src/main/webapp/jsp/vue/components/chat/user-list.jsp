<script type="text/x-template" id="user-list-template">
    <div class="user-list">
        <div v-for="user in users" class="row">
            USER
        </div>
    </div>
</script>

<script>
    Vue.component('user-list', {
        template: "#user-list-template",
        data() {
            return {
                users: [1, 2, 3],
                chat: []
            }
        },
        methods: {
            fetchUsers() {
                axios.get("/rest/user/get")
                     .then((response) => {
                         console.log(response);
                         if (response.data.length > 0) {
                             this.users = response.data;
                         }
                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },

            fetchChat(chatId) {
                axios.get("/rest/chat/get?chatId=" + chatId)
                     .then((response) => {
                         console.log(response);
                         if (response.data.length > 0) {
                             this.chat = response.data;
                         }
                     }).catch((error) => {
                        console.log(error);
                    }
                )
            },
        },
        mounted: function () {
            console.log("user list mounted");
            this.fetchUsers();
        },
        computed: {}
    })
</script>

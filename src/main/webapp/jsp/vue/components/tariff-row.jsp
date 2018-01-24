<script type="text/x-template" id="tariff-row-template">
    <div>
        here you are
        <template v-if="isAdmin">

        </template>

        <template v-else>

        </template>
    </div>
</script>

<script>
    Vue.component('tariff-row', {
        props: [
            'id', 'title', 'description', 'number_of_days', 'cost', 'currency', 'isAdmin',
            'subscribers',
            'banned',
            'subscriptions',
            'user_id',
        ],
        data: function () {
            return {
                edit: false,
                isSubscribed: false,
                isAdmin: false
            }
        },
        template: "#tariff-row-template",
        methods: {
            startEdit() {
                console.log("Start edit");
                this.edit = true;
            },
            finishEdit() {
                console.log("finish edit");
                this.edit = false;
            },

            subscribe() {
                console.log(this.user_id);
                console.log(this.id);
                axios.post("/rest/user/subscribe", {
                    'userId': this.user_id,
                    'tariffId': this.id
                }).then((response) => {
                    console.log(response);
                    this.isSubscribed = true;
                }).catch((error) => {
                    console.log(error);
                })

            },

            unsubscribe() {
                this.isSubscribed = false;
            },

        },

        beforeMount() {
            this.isSubscribed = this.subscribed == "true";
            this.isAdmin = this.role == "admin";
        },
        mounted: function () {

        },

        computed: {
            buttonText() {
                if (!this.isAdmin && this.isSubscribed) {
                    return this.unsubscribe_text;
                }
                else if (!this.isAdmin && !this.isSubscribed) {
                    return this.subscribe_text;
                }
                else if (this.isAdmin && this.edit) {
                    return this.save_text;
                }
                else {
                    return this.edit_text;
                }
            }
        }
    })
</script>
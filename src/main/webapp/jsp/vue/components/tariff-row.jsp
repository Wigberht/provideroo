<script type="text/x-template" id="tariff-row-template">

    <div class="row zero-margin center-align">
        <div class="col s2">{{title}}</div>
        <div class="col s2">{{description}}</div>
        <div class="col s2">{{number_of_days}}</div>
        <div class="col s1">{{cost}}</div>
        <div class="col s2">{{currency}}</div>
        <div class="col s2"></div>
        <div class="col s2">
            <a v-if="isAdmin && !edit"
               @click="startEdit"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
            <a v-if="isAdmin && edit"
               @click="finishEdit"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
            <a v-if="!isAdmin && !isSubscribed"
               @click="subscribe"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
            <a v-if="!isAdmin && isSubscribed"
               @click="unsubscribe"
               class="waves-effect waves-light btn">
                {{buttonText}}
            </a>
        </div>
    </div>

</script>

<script>
    Vue.component('tariff-row', {
        props: [
            'id',
            'title', 'description',
            'number_of_days', 'cost',
            'currency', 'role',
            'edit_text', 'save_text',
            'subscribe_text', 'unsubscribe_text',
            'subscribed', 'user_id',
            'subscription_fail_text'
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